package Board;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Board {
    private int bno;
    private String btitle;
    private String bcontent;
    private String bwriter;
    private Date bdate;

    public Board(){
        this.bdate = getCurrentDate();
    }

    public Board(int bno, String btitle, String bcontent, String bwriter, Date bdate) {
        this.bno = bno;
        this.btitle = btitle;
        this.bcontent = bcontent;
        this.bwriter = bwriter;
        this.bdate = (bdate != null) ? bdate : getCurrentDate();
    }

    private Date getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String formattedDate = sdf.format(new Date());

        try {
            return sdf.parse(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        return sdf.format(this.bdate);
    }

    @Override
    public String toString() {
        return bno +  "     " + bwriter + "     " + getFormattedDate() + "      " + btitle;

    }

    //입력 받을 Date를 어떻게 json파일에 기입할지

}
