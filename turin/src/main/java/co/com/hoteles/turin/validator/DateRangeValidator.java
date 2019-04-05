package co.com.hoteles.turin.validator;

import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("dateRangeValidator")
public class DateRangeValidator implements Validator {

   public void validate(FacesContext facesContext, UIComponent component,
           Object value) throws ValidatorException {

       UIInput dateIniComponent = (UIInput) component.getAttributes().get("dateIniComponent");
       UIInput dateFinComponent = (UIInput) component.getAttributes().get("dateFinComponent");
       String range = ((String) component.getAttributes().get("range")).toLowerCase();
       String reference = ((String) component.getAttributes().get("reference")).toLowerCase();

       if (value == null) {
           return;
       } else if (value instanceof Date) {
           Date dateIni = null;
           Date dateFin = null;
           if ((dateIniComponent == null) && (dateFinComponent != null)) {
               if (!dateFinComponent.isValid()) {
                   return; //No hay datos contra quien comparar
               }
               dateFin = (Date) dateFinComponent.getValue();
               dateIni = (Date) value;
           }

           if ((dateFinComponent == null) && (dateIniComponent != null)) {
               if (!dateIniComponent.isValid()) {
                   return; //No hay datos contra quien comparar
               }
               dateIni = (Date) dateIniComponent.getValue();
               dateFin = (Date) value;
           }

           if ((dateIni != null) && (dateFin != null)) {
               Calendar cal = Calendar.getInstance();

               cal.setTime(dateIni);
               Integer yearIni = cal.get(Calendar.YEAR);
               Integer monthIni = cal.get(Calendar.MONTH);
               Long daysMonthIni = (long) YearMonth.of(yearIni, monthIni + 1).lengthOfMonth();
               Long daysYearIni = (long) cal.getActualMaximum(Calendar.DAY_OF_YEAR);

               cal.setTime(dateFin);
               Integer yearFin = cal.get(Calendar.YEAR);
               Integer monthFin = cal.get(Calendar.MONTH);
               Long daysMonthFin = (long) YearMonth.of(yearFin, monthFin + 1).lengthOfMonth();
               Long daysYearFin = (long) cal.getActualMaximum(Calendar.DAY_OF_YEAR);

               Long daysAllowed =
                       ("year".equals(range) ? ("ini".equals(reference)?daysYearIni:("fin".equals(reference)?daysYearFin:null)) :
                       ("month".equals(range) ? ("ini".equals(reference)?daysMonthIni:("fin".equals(reference)?daysMonthFin:null)) : 
                       ("week".equals(range) ? 7 : null)));

               Long daysBetweenDates = TimeUnit.DAYS.convert(dateFin.getTime() - dateIni.getTime(), TimeUnit.MILLISECONDS);

               if (daysAllowed == null) {
                   FacesMessage facesMessage
                           = new FacesMessage(
                                   FacesMessage.SEVERITY_ERROR,
                                   "Rango de fechas mal expresado en el facelet (vista) ",
                                   "Rango de fechas mal expresado en el facelet (vista) ");
                   throw new ValidatorException(facesMessage);
               }

               if (dateFin.before(dateIni)) {
                   FacesMessage facesMessage
                           = new FacesMessage(
                                   FacesMessage.SEVERITY_ERROR,
                                   "Fecha Final No es posterior a Fecha Inicial ",
                                   "La Fecha Final debe ser posterior a Fecha Inicial");
                   throw new ValidatorException(facesMessage);
               }

               if (daysBetweenDates > daysAllowed) {
                   FacesMessage facesMessage
                           = new FacesMessage(
                                   FacesMessage.SEVERITY_ERROR,
                                   "Se ha excedido los dias permitidos " + daysAllowed + " entre fechas seleccionadas, entre las fechas hay " + daysBetweenDates + " dias",
                                   "entre las fechas hay " + daysBetweenDates + " dias");
                   throw new ValidatorException(facesMessage);
               }
           }

       }
   }
   }