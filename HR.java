import java.util.*;
import java.sql.*;
// import java.text.*;

class DBConfig {
    private static final String URL = "jdbc:mysql://localhost:3306/emp_management";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    // private static final String DRIVER ="con.mysql.jdbc.Driver";
    

    public static Connection getConnection() throws SQLException {
        
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

class employee 
{
    int emp_id;
    String First_name;
    String Last_name;
    int salary;
    int age;
    String qualification;
    String department;
    
    public employee(int emp_id, String first_name, String last_name, int salary, int age,String qualification,String department) {
        this.emp_id = emp_id;
        First_name = first_name;
        Last_name = last_name;
        this.salary = salary;
        this.age = age;
        this.qualification=qualification;
        this.department=department;

    }
    public int getEmp_id() {
        return emp_id;
    }
    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }
    public String getFirst_name() {
        return First_name;
    }
    public void setFirst_name(String first_name) {
        First_name = first_name;
    }
    public String getLast_name() {
        return Last_name;
    }
    public void setLast_name(String last_name) {
        Last_name = last_name;
    }
    
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    
    public String getQualification() {
        return qualification;
    }
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    @Override
    public String toString() {
        return "employee [emp_id=" + emp_id + ", First_name=" + First_name + ", Last_name=" + Last_name + ", salary="
                + salary + ", age=" + age + ", qualification=" + qualification + ", department=" + department + "]";
    }

    
}

public class HR 
{
    Scanner sc=new Scanner(System.in);
    static String password="1234HR";


   ArrayDeque1 emp;
 int size=1000;
   public HR()
   {
    size ++;
    emp=new ArrayDeque1(size);
    
   }




    public  void add_employee() throws Exception
    {
        
        int emp_id=0;
        String id;
        System.out.println("enter id");
        boolean b=false;
        while (!b) {
            try
            {
                
                id=sc.nextLine();
                for(int i=0;i<id.length();i++)
                {
                    if((id.charAt(i)>='a' && id.charAt(i)<='z')||(id.charAt(i)>='A' && id.charAt(i)<='Z'))
                    {
                        throw new InvalidInputException("enter only integer value");
                    }
                    else{
                        b=true;
                        emp_id=Integer.parseInt(id);
                    }
                }
            }
            catch(InvalidInputException e)
            {
                System.out.println(e);
                continue;
    
            }
        }
       
        System.out.println("enter first name");
        String First_name=sc.next();
        System.out.println("enter last name");
        String Last_name=sc.next();
        System.out.println("enter employee salary");
        String salary1=null;
        int salary=0;

        boolean a=false;
        while(!a)
        {
            try
            {
                salary1=sc.next();
                for(int i=0;i<salary1.length();i++)
                {
                    if((salary1.charAt(i)>='a' && salary1.charAt(i)<='z')||(salary1.charAt(i)>='A' && salary1.charAt(i)<='Z'))
                    {
                        throw new InvalidInputException("enter valid salary only in integer");
                    }
                    else{
                        a=true;
                        salary=Integer.parseInt(salary1);
                    }
                }
    
            }
            catch(InvalidInputException e)
            {
                System.out.println(e);
            }
          

        }
        System.out.println("enter employee age");

        int age=0;
        String age1=null;
        boolean c=false;
        while (!c) {
            try {
                age1=sc.next();
                for(int i=0;i<age1.length();i++)
                {
                    if((age1.charAt(i)>='a' && age1.charAt(i)<='z')||(age1.charAt(i)>='A' && age1.charAt(i)<='Z') )
                    {
                        throw new InvalidInputException("enter valid age only in integer");
                    }
                    else{
                        c=true;
                        age=Integer.parseInt(age1);
                    }
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }
        System.out.println("enter qualification of employee");
        String qualification=sc.next();
        System.out.println("enter department of employee");
        String department =sc.next();
        emp.add(emp_id);
        Connection con = DBConfig.getConnection();
        String query="insert into employee_data values(?,?,?,?,?,?,?)";
        PreparedStatement ps=con.prepareStatement(query);
        ps.setInt(1, emp_id);
        ps.setString(2, First_name);
        ps.setString(3, Last_name);
        ps.setInt(4,salary);
        ps.setInt(5,age);
        ps.setString(6,qualification);
        ps.setString(7,department);
        ps.executeUpdate();


        String name =First_name+Last_name;
        String sql1="insert into emp_attendance values(?,?,?,?)";
        PreparedStatement pst1=con.prepareStatement(sql1);
        pst1.setInt(1, emp_id);
        pst1.setString(2,name);
        pst1.setInt(3,0);
        pst1.setInt(4,0);
        pst1.executeUpdate();


        String sql2="insert into salary  values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pst=con.prepareStatement(sql2);
        pst.setInt(1,emp_id);
        pst.setString(2, name);
        pst.setInt(3, salary);
        pst.setDouble(4,0);
        pst.setDouble(5,0);
        pst.setDouble(6,0);
        pst.setDouble(7,0);
        pst.setDouble(8,0);
        pst.setDouble(9,0);
        pst.setDouble(10,0);
        pst.setDouble(11,0);
        pst.setDouble(12,0);
        pst.setDouble(13,0);
        pst.setDouble(14,0);
        pst.setDouble(15,0);
        pst.executeUpdate();

    }



    public void remove_employee()throws Exception
    {
        Connection con=DBConfig.getConnection();
        System.out.println("enter employee id which you want to remove");
        int id=0;
        String id1=null;
        boolean b=false;
        while (!b) {
            try
            {
                
                id1=sc.nextLine();
                for(int i=0;i<id1.length();i++)
                {
                    if((id1.charAt(i)>='a' && id1.charAt(i)<='z')||(id1.charAt(i)>='A' && id1.charAt(i)<='Z'))
                    {
                        throw new InvalidInputException("enter only integer value");
                    }
                    else{
                        b=true;
                        id=Integer.parseInt(id1);
                    }
                }
            }
            catch(InvalidInputException e)
            {
                System.out.println(e);
                continue;
    
            }
        }
        String sql="delete from employee_data where emp_id=?";
        PreparedStatement pst=con.prepareStatement(sql);
        pst.setInt(1, id);
        pst.executeUpdate();
        
        String sql1="delete from emp_attendance where id=?";
        PreparedStatement pst1=con.prepareStatement(sql1);
        pst1.setInt(1, id);
        pst1.executeUpdate();


        String sql2="delete from salary where id=?";
        PreparedStatement pst2=con.prepareStatement(sql2);
        pst2.setInt(1,id);
        pst2.executeUpdate();


        System.out.println("employee removed successfull");
    }


    public void display()throws Exception
    {
        Connection con=DBConfig.getConnection();
        String sql="select * from employee_data";
        PreparedStatement pst=con.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();
        while (rs.next()) {
            System.out.println("emp_id="+rs.getInt(1));
            System.out.println("firstname="+rs.getString(2));
            System.out.println("lastname="+rs.getString(3));
            System.out.println("salary="+rs.getInt(4));
            System.out.println("age="+rs.getInt(5));
            System.out.println("qualification="+rs.getString(6));
            System.out.println("department="+rs.getString(7));
        }

    }
    

    public void displayById()throws Exception
    {
        System.out.println("enter employee id which you have the data");
        int id=sc.nextInt();
        String id1=null;
        boolean b=false;
        while (!b) {
            try
            {
                
                id1=sc.nextLine();
                for(int i=0;i<id1.length();i++)
                {
                    if((id1.charAt(i)>='a' && id1.charAt(i)<='z')||(id1.charAt(i)>='A' && id1.charAt(i)<='Z'))
                    {
                        throw new InvalidInputException("enter only integer value");
                    }
                    else{
                        b=true;
                        id=Integer.parseInt(id1);
                    }
                }
            }
            catch(InvalidInputException e)
            {
                System.out.println(e);
                continue;
    
            }
        }
        Connection con=DBConfig.getConnection();
        String sql="select * from employee_data where emp_id=?";
        PreparedStatement pst=con.prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs=pst.executeQuery();
        while (rs.next()) {
            System.out.println("emp_id="+rs.getInt(1));
            System.out.println("firstname="+rs.getString(2));
            System.out.println("lastname="+rs.getString(3));
            System.out.println("salary="+rs.getInt(4));
            System.out.println("age="+rs.getInt(5));
            System.out.println("qualification="+rs.getString(6));
            System.out.println("department="+rs.getString(7));
        }
        
    }



    
    void emp_attendance()throws Exception
    {
        Connection con=DBConfig.getConnection();
        System.out.println("enter total working days of this month");
        int totalWorkingDay=sc.nextInt();

        String sql4="select  total_present , total_absent from emp_attendance";
        PreparedStatement pst4=con.prepareStatement(sql4);
        ResultSet rs3=pst4.executeQuery();
        int totalday=0;
        boolean b=false;
        while(!b)
        {
             totalday=rs3.getInt(1) + rs3.getInt(2);
        }
        if(totalWorkingDay==totalday)
        {
             String sql="truncate table emp_attendance";
             PreparedStatement pst5=con.prepareStatement(sql);
             pst5.execute();
        }
        
        String sql2 ="call countOfEmployee(?)";
        CallableStatement cst=con.prepareCall(sql2);
        cst.executeQuery();
        int size=cst.getInt(1);
        
        if(size==0){
            
            String sql="select emp_id,first_name,last_name from employee_data";
            PreparedStatement pst=con.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            ArrayDeque1 emp_id_atd=new ArrayDeque1(size);
            while (rs.next()) 
            {
                int id_atd=rs.getInt(1);
                String first_name =rs.getString(2);
                String last_name =rs.getString(3);
                String name =first_name+last_name;
                String sql1="insert into emp_attendance values(?,?,?,?)";
                PreparedStatement pst1=con.prepareStatement(sql1);
                pst1.setInt(1,id_atd);
                pst1.setString(2, name);
                pst1.setInt(3,0);
                pst1.setInt(4,0);
                pst1.executeUpdate();
                emp_id_atd.add(id_atd);            
            }
            attendance(emp_id_atd);
        }
        else
        {
            
            String sql3="select id from emp_attendance";
            PreparedStatement pst3=con.prepareStatement(sql3);
            ArrayDeque1 emp_id_atd1=new ArrayDeque1(size);
            ResultSet rs=pst3.executeQuery();
            while (rs.next()) {
                int id_atd=rs.getInt(1);
                emp_id_atd1.add(id_atd);            
            }
            attendance(emp_id_atd1);
            
        }
    }





    void attendance(ArrayDeque1 emp_id_atd)throws Exception
    {
        
        Connection con=DBConfig.getConnection();
        String sql2 ="call countOfEmployee(?)";
        CallableStatement cst=con.prepareCall(sql2);
        cst.executeQuery();
        int size=cst.getInt(1);
        while(size>0)
        {
            int id=(int) emp_id_atd.pollFirst();
            size--;
            System.out.println("enter the status of emp"+ id );
            String status=sc.next();
            if (status.equals("p"))
            {
                String sql="update emp_attendance set total_present=total_present+1 where id=?";
                PreparedStatement pst=con.prepareStatement(sql);
                pst.setInt(1, id);
                pst.executeUpdate();
            }
            else
            {
                String sql="update emp_attendance set total_absent=total_absent+1 where id=?";
                PreparedStatement pst=con.prepareStatement(sql);
                pst.setInt(1, id);
                pst.executeUpdate();
            }

        }
        
    }


    void leavemanagement()throws Exception
    {
        Connection con=DBConfig.getConnection();
        System.out.println("enter emploee id who need leave");
        int id=0;

        String id1=null;
        boolean b=false;
        while (!b) {
            try
            {
                
                id1=sc.nextLine();
                for(int i=0;i<id1.length();i++)
                {
                    if((id1.charAt(i)>='a' && id1.charAt(i)<='z')||(id1.charAt(i)>='A' && id1.charAt(i)<='Z'))
                    {
                        throw new InvalidInputException("enter only integer value");
                    }
                    else{
                        b=true;
                        id=Integer.parseInt(id1);
                    }
                }
            }
            catch(InvalidInputException e)
            {
                System.out.println(e);
                continue;
    
            }
        }
        String sql1="select name from emp_attendance where id=?";
        PreparedStatement pst1=con.prepareStatement(sql1);
        pst1.setInt(1, id);
        ResultSet rs=pst1.executeQuery();
        if(!rs.next())
        {
            System.out.println("please enter valid id");
        }
        else
        {
            while (rs.next()) 
            {
                String name =rs.getString(1);
                System.out.println("enter the number of leave days");
                int leave_days=sc.nextInt();
                System.out.println("enter the start date int the fromat of yyyy-mm-dd");
                String start_date=sc.next();
                System.out.println("enter end date in the format like this yyyy-mm-dd");
                String end_date=sc.next();
                System.out.println("enter status of leave if you want to approve or not...");
                String status=sc.next();
                String sql2="insert into leave_table values(?,?,?,?,?,?)";
                PreparedStatement pst2=con.prepareStatement(sql2);
                pst2.setInt(1,id);
                pst2.setString(2,name);
                pst2.setInt(3, leave_days);
                pst2.setString(4, start_date);
                pst2.setString(5, end_date);
                pst2.setString(6, status);
                pst2.executeUpdate();
            }
           
        }


    }


    public void ShowLeaveData()throws Exception
    {
        Connection con=DBConfig.getConnection();
        String sql="select * from leave_table ";
        PreparedStatement pst=con.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();
        while (rs.next()) 
        {
            System.out.println("id=" + rs.getInt(1));
            System.out.println("name=" + rs.getString(2));
            System.out.println("total leave days=" + rs.getInt(3));
            System.out.println("start date="+ rs.getString(4));
            System.out.println("end date="+rs.getString(5));
            System.out.println("status="+ rs.getString(6));
        }
    }




    void showattendance()throws Exception
    {
        Connection con=DBConfig.getConnection();

        String sql="select * from emp_attendance";
        PreparedStatement pst=con.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();
        while(rs.next())
        {
            System.out.println("id="+rs.getInt(1));
            System.out.println("name="+rs.getString(2));
            System.out.println("total presant="+rs.getInt(3));
            System.out.println("total absent="+rs.getInt(4));
        }
    }



    public void genratesalary()throws Exception
    {
        Connection con=DBConfig.getConnection();
        String sql1="select total_absent from emp_attendance ";
        PreparedStatement pst=con.prepareStatement(sql1);
        ResultSet rs=pst.executeQuery();
        ArrayDeque <Integer> totalABbyID=new ArrayDeque<>(); 
        while (rs.next()) {
            int  ABbyID=rs.getInt(1);
            totalABbyID.add(ABbyID);
        }   
        String sql2="select id,name,actual_salary from salary ";
        PreparedStatement pst2=con.prepareStatement(sql2);
        ResultSet rs2=pst2.executeQuery();
        while(rs2.next())
        {
            int ab_number=totalABbyID.pollFirst();
            int id=rs2.getInt(1);
            String name=rs2.getString(2);
            double salary=rs2.getInt(3);
            if(ab_number<=2)
            {
                salary=salary-0;
            }
            else if(ab_number>2 && ab_number<=29)
            {
                salary=salary-salary*ab_number/30;
            }
            else 
            {
                salary=0;
            }
            System.out.println("enter month");
            String month = sc.next();
            sc.nextLine();
            if(month.equals("jan"))
            {
                System.out.println(salary);
                String sql="update salary set jan="+salary+" where id="+id+"";
                Statement pst3=con.createStatement();
                pst3.executeUpdate(sql);
            }

            else if(month.equals("feb"))
            {
                System.out.println(salary);
                String sql="update salary set feb="+salary+" where id="+id+"";
                Statement pst3=con.createStatement();
                pst3.executeUpdate(sql);
            }

            else if(month.equals("mar"))
            {
                System.out.println(salary);
                String sql="update salary set mar="+salary+" where id="+id+"";
                Statement pst3=con.createStatement();
                pst3.executeUpdate(sql);
            }

            
            else if(month.equals("apr"))
            {
                System.out.println(salary);
                String sql="update salary set apr="+salary+" where id="+id+"";
                Statement pst3=con.createStatement();
                pst3.executeUpdate(sql);
            }
            
            
            else if(month.equals("may"))
            {
                System.out.println(salary);
                String sql="update salary set may="+salary+" where id="+id+"";
                Statement pst3=con.createStatement();
                pst3.executeUpdate(sql);
            }

            
            else if(month.equals("jun"))
            {
                System.out.println(salary);
                String sql="update salary set jun="+salary+" where id="+id+"";
                Statement pst3=con.createStatement();
                pst3.executeUpdate(sql);
            }

            
            else if(month.equals("jul"))
            {
                System.out.println(salary);
                String sql="update salary set jul="+salary+" where id="+id+"";
                Statement pst3=con.createStatement();
                pst3.executeUpdate(sql);
            }

            
            else if(month.equals("aug"))
            {
                System.out.println(salary);
                String sql="update salary set aug="+salary+" where id="+id+"";
                Statement pst3=con.createStatement();
                pst3.executeUpdate(sql);
            }

            
            else if(month.equals("sep"))
            {
                System.out.println(salary);
                String sql="update salary set sep="+salary+" where id="+id+"";
                Statement pst3=con.createStatement();
                pst3.executeUpdate(sql);
            }

            
            else if(month.equals("oct"))
            {
                System.out.println(salary);
                String sql="update salary set oct="+salary+" where id="+id+"";
                Statement pst3=con.createStatement();
                pst3.executeUpdate(sql);
            }

            
            else if(month.equals("nov"))
            {
                System.out.println(salary);
                String sql="update salary set nov="+salary+" where id="+id+"";
                Statement pst3=con.createStatement();
                pst3.executeUpdate(sql);
            }

            
            else if(month.equals("dec"))
            {
                System.out.println(salary);
                String sql="update salary set dec="+salary+" where id="+id+"";
                Statement pst3=con.createStatement();
                pst3.executeUpdate(sql);
            }

        }

    }


    public void updateemployeedata()throws Exception
    {
        Connection con=DBConfig.getConnection();
        System.out.println("enter id for update data");
        int id=0;
        String id1=null;
        boolean b=false;
        while (!b) {
            try
            {
                
                id1=sc.nextLine();
                for(int i=0;i<id1.length();i++)
                {
                    if((id1.charAt(i)>='a' && id1.charAt(i)<='z')||(id1.charAt(i)>='A' && id1.charAt(i)<='Z'))
                    {
                        throw new InvalidInputException("enter only integer value");
                    }
                    else{
                        b=true;
                        id=Integer.parseInt(id1);
                    }
                }
            }
            catch(InvalidInputException e)
            {
                System.out.println(e);
                continue;
    
            }
        }
        
         
        
       

        String choice = null;
        String sql1="select * from employee_data where emp_id=?";
        PreparedStatement pst1=con.prepareStatement(sql1);
        pst1.setInt(1, id);
        ResultSet rs=pst1.executeQuery();
        if(!rs.next())
        {
            System.out.println("enter valid id....");
        }
        else
        {
            do
            {
                System.out.println("1. for change first name");
                System.out.println("2. for change last name");
                System.out.println("3. for change salary");
                System.out.println("4. for change age");
                System.out.println("5. for change qualification");
                System.out.println("6. for department");
                System.out.println("7. for exit");

                try{
                    choice=sc.next();
                    if (!(choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4") || choice.equals("5") || choice.equals("6") || choice.equals("7")))
                    {
                      throw new InvalidInputException("please eneter valid number");
                    }
                    }
                    
                    catch(InvalidInputException e)
                    {
                        System.out.println(e);
                        continue;
                    }
                    
               
                switch (choice) {
                    case "1":
                        System.out.println("enter new first name ");
                        String n_firstname=sc.next();
                        String sql="update employee_data set first_name=? where id=?";
                        PreparedStatement pst=con.prepareStatement(sql);
                        pst.setString(1, n_firstname);
                        pst.setInt(2, id);
                        pst.executeUpdate();
                        System.out.println("first name is updated");
                        break;
                    
                    case "2":
                        System.out.println("enter new last name");
                        String n_lastname=sc.next();
                        String sql2="update employee_data set last_name=? where id=? ";
                        PreparedStatement pst2=con.prepareStatement(sql2);
                        pst2.setString(1, n_lastname);
                        pst2.setInt(2, id);
                        break;

                    case "3":
                        System.out.println("enter new salary");
                        int n_salary=sc.nextInt();
                        String sql3="update employee_data set salary =? where  id=?";
                        PreparedStatement pst3=con.prepareStatement(sql3);
                        pst3.setInt(1,n_salary);
                        pst3.setInt(2,id);
                        pst3.executeUpdate();
                        break;

                    case "4":
                        System.out.println("enter new age");
                        int n_age=sc.nextInt();
                        String sql4="update employee_data set age=? where id=?";
                        PreparedStatement pst4=con.prepareStatement(sql4);
                        pst4.setInt(1, n_age);
                        pst4.setInt(2, id);
                        pst4.executeUpdate();
                        break;

                    case "5":
                        System.out.println("enter new qualification");
                        String n_qualification = sc.next();
                        String sql5="update employee_data set qualification=? where id=?";
                        PreparedStatement pst5=con.prepareStatement(sql5);
                        pst5.setString(1,n_qualification);
                        pst5.setInt(2,id);
                        pst5.executeUpdate();
                        break;

                    case "6":
                        System.out.println("enter new department");
                        String n_department=sc.next();
                        String sql6="update employee_data set department=? where id=?";
                        PreparedStatement pst6=con.prepareStatement(sql6);
                        pst6.setString(1, n_department);
                        pst6.setInt(2, id);
                        pst6.executeUpdate();
                        break;

                    case "7":
                        System.out.println("exting...");
                        break;
                    default:
                        break;
                }
    
            }while(!choice.equals("7"));
        }
       
        
    }

}


class InvalidInputException extends RuntimeException
{
    InvalidInputException(String s)
    {
        super(s);
    }
}
