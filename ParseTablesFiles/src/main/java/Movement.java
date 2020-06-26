import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Movement {

    private String nameOrganisationOperation;
    private BigDecimal coming;
    private BigDecimal expenditure;
    private LocalDate dateOperation;
    private DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yy");

    public Movement(String dateOperation, String nameOrganisationOperation, String coming, String expenditure) {
        this.dateOperation = LocalDate.parse(dateOperation, format);
        this.nameOrganisationOperation = nameOrganisationOperation;
        this.coming = BigDecimal.valueOf(Double.parseDouble(coming));
        this.expenditure = BigDecimal.valueOf(Double.parseDouble(expenditure));
    }

    public Movement() {

    }

    public BigDecimal getComing() {
        return coming;
    }

    public void setComing(String coming) {
        this.coming = BigDecimal.valueOf(Double.parseDouble(coming));
    }

    public BigDecimal getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(String expenditure) {
        this.expenditure = BigDecimal.valueOf(Double.parseDouble(expenditure));
    }

    public String getDateOperation() {
        return format.format(dateOperation);
    }

    public void setDateOperation(String dateOperation) {
        this.dateOperation = LocalDate.parse(dateOperation, format);
    }

    public String getNameOrganisationOperation() {
        return nameOrganisationOperation;
    }

    public void setNameOrganisationOperation(String nameOrganisationOperation) {
        this.nameOrganisationOperation = nameOrganisationOperation;
    }
}
