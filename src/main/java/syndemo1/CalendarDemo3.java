package syndemo1;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarDemo3 {

    public static void main(String[] args) {
        Calendar ca = Calendar.getInstance();
        System.out.println(ca + "+++++++1");//打印结果如上注释
        //获得年
        //语法：ca.get(域)，域的语法：Calendar.来取得
        int year = ca.get(Calendar.YEAR);
        //获得月    month在中国用要加一！！
        int month = ca.get(Calendar.MONTH) + 1;
        //获得日
        int day = ca.get(Calendar.DAY_OF_MONTH);
        //获得时
        int hour = ca.get(Calendar.HOUR_OF_DAY);
        //获得分
        int minute = ca.get(Calendar.MINUTE);
        //获得秒
        int second = ca.get(Calendar.SECOND);
        System.out.println(year + "年" + month + "月" + day + "日" +
                "\t" + hour + "时" + minute + "分" + second + "秒");

        //设置日历
        Calendar ca1 = Calendar.getInstance();
        //第一个参数：具体的域，如：year；第二个参数，具体值
        ca1.set(Calendar.YEAR, 1985);
        //注意一个细节：下面设置的是4月，打印出来会是5月。
        ca1.set(Calendar.MONTH, 04);
        ca1.set(Calendar.DAY_OF_MONTH, 22);
        ca1.set(Calendar.HOUR_OF_DAY, 6);
        ca1.set(Calendar.MINUTE, 23);
        ca1.set(Calendar.SECOND, 34);
        System.out.println(ca1 + "[[[[[[[");

        //获得设置的日历的毫秒数
        long tm = ca1.getTimeInMillis();

        //把毫秒数变成对应的时间
        //第一种。没有格式化的形式打印
        Date date5 = new Date(tm);
        System.out.println(date5);

        //第二种。自己设定格式打印（即格式化后打印）
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str5 = sdf.format(date5);
        System.out.println(str5);
    }
}
