package LessonDesign;
import java.sql.*;
public class Intial {
    public void Intialization(){
        String JDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
//SQL数据库引擎
        String connectDB="jdbc:sqlserver://DESKTOP-ODSKH3J\\SQLEXPRESS:1433;DatabaseName=master";
//数据源  ！！！！注意若出现加载或者连接数据库失败一般是这里出现问题
        // 我将在下面详述
        try {
            //加载数据库引擎，返回给定字符串名的类
            Class.forName(JDriver);
        }catch(ClassNotFoundException e)
        {
            //e.printStackTrace();
            System.out.println("加载数据库引擎失败");
            System.exit(0);
        }
        System.out.println("数据库驱动成功");

        try {
            String user="sa";
            String password="zlk";
            Connection con=DriverManager.getConnection(connectDB,user,password);
//连接数据库对象
            System.out.println("连接数据库成功");
            Statement stmt=con.createStatement();
//创建SQL命令对象

            //创建表
            System.out.println("开始创建表");
            //创建表SQL语句
            String query= "create table TABLE1(ID NCHAR(10),NAME NCHAR(10), GENDER NCHAR(10), MAJOR NCHAR(15), SCORE INT)";
            stmt.executeUpdate(query);//执行SQL命令对象
            System.out.println("表创建成功");

            //输入数据
            System.out.println("开始插入数据");
            String a1="INSERT INTO TABLE1 VALUES(129386001,'朱祁钰','男','通信工程', 10)";
            //插入数据SQL语句
            String a2="INSERT INTO TABLE1 VALUES(129386002,'朱祁镇','男','通信工程', 12)";
            String a3="INSERT INTO TABLE1 VALUES(129386003,'朱宸濠','男','通信工程', 13)";
            String a4="INSERT INTO TABLE1 VALUES(129386004,'朱由校','男','通信工程', 14)";
            String a5="INSERT INTO TABLE1 VALUES(129386005,'朱由检','男','通信工程', 15)";
            String a6="INSERT INTO TABLE1 VALUES(129386006,'朱瞻基','男','通信工程', 10)";
            String a7="INSERT INTO TABLE1 VALUES(129386007,'朱高炽','男','通信工程', 12)";
            String a8="INSERT INTO TABLE1 VALUES(129386008,'朱祐樘','男','通信工程', 15)";
            String a9="INSERT INTO TABLE1 VALUES(129386009,'朱厚照','男','通信工程', 19)";
            String a10="INSERT INTO TABLE1 VALUES(129386010,'朱载坖','男','通信工程', 20)";
            String a11="INSERT INTO TABLE1 VALUES(129386011,'朱翊钧','男','通信工程', 10)";
            String a12="INSERT INTO TABLE1 VALUES(129386012,'朱常洛','男','通信工程', 20)";
            String a13 = "INSERT INTO TABLE1 VALUES(129386013, '朱棣', '男', '通信工程', 15)";
            String a14 = "INSERT INTO TABLE1 VALUES(129386014, '朱长乐', '男', '通信工程', 15)";
            String a15 = "INSERT INTO TABLE1 VALUES(129386013, '刘彻', '男', '通信工程', 15)";
            String a16 = "INSERT INTO TABLE1 VALUES(129386013, '慕容燕', '女', '通信工程', 15)";
            String a17 = "INSERT INTO TABLE1 VALUES(129386013, '慕容嫣', '女', '通信工程', 15)";
            String a18 = "INSERT INTO TABLE1 VALUES(129386013, '欧阳锋', '男', '通信工程', 15)";

            stmt.executeUpdate(a1);//执行SQL命令对象
            stmt.executeUpdate(a2);
            stmt.executeUpdate(a3);
            stmt.executeUpdate(a4);
            stmt.executeUpdate(a5);
            stmt.executeUpdate(a6);
            stmt.executeUpdate(a7);
            stmt.executeUpdate(a8);
            stmt.executeUpdate(a9);
            stmt.executeUpdate(a10);
            stmt.executeUpdate(a11);
            stmt.executeUpdate(a12);
            stmt.executeQuery(a13);
            stmt.executeQuery(a14);
            stmt.executeQuery(a15);
            stmt.executeQuery(a16);
            stmt.executeQuery(a16);
            stmt.executeQuery(a17);
            stmt.executeQuery(a18);
            System.out.println("插入数据成功");

            //读取数据
            System.out.println("开始读取数据");
            ResultSet rs=stmt.executeQuery("SELECT * FROM TABLE1");//返回SQL语句查询结果集(集合)
                    //循环输出每一条记录
            while(rs.next()){
                //输出每个字段
                System.out.println(rs.getString("ID")+"\t"+rs.getString("NAME"));
            }
            System.out.println("读取完毕");

            //关闭连接
            stmt.close();//关闭命令对象连接
            con.close();//关闭数据库连接
        }catch(SQLException e){
            e.printStackTrace();
            System.out.print(e.getErrorCode());
            //System.out.println("数据库连接错误");
            System.exit(0);
        }
    }
}
