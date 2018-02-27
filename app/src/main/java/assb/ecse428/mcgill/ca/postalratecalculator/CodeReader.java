package assb.ecse428.mcgill.ca.postalratecalculator;

/**
 * Created by lucien on 2018-02-26.
 */

public class CodeReader {


    private String from;
    private String to;
    private String code;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "CodeReader{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
