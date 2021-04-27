package sample.connect;

import java.sql.*;

public class DatabaseHandler {

    Connection dbConnection = null;

    public Connection getDbConnection()
            throws ClassNotFoundException,SQLException{
        String connectionString = "jdbc:oracle:thin:@localhost:1521:orcl";
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC Driver is not found");
            e.printStackTrace();
        }


        try {
            dbConnection = DriverManager.getConnection(connectionString, "Dima", "45");
        } catch (SQLException e) {
            System.out.println("Connection Failed : " + e.getMessage());
        }

        return dbConnection;
    }

    public void addUser(String dateBirthday, String telephone, String email, String name, String patronymic, String surname){
        String insert = "INSERT INTO "  + Const.READER_TABLE + " ("  + Const.READER_DATE_BIRTHDAY + ", " + Const.READER_TELEPHONE + ", " + Const.READER_EMAIL + ", " + Const.READER_NAME + ", " +
                Const.READER_PATRONYMIC + ", " + Const.READER_SURNAME + ") "+
                "VALUES(to_date('" + dateBirthday +"', 'DD.MM.YYYY'),'" + telephone +"','" + email +"','" + name +"','" + patronymic +"','" + surname +"')";
        System.out.println(insert);


        try{
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public ResultSet choiceBooks(String nameBook, String nameA, String nameS, String nameP){
        ResultSet resSet = null;
        String select = "SELECT I.ID_I, B."  + Const.BOOKS_NAME + ", A."  + Const.AUTHOR_NAME + " as NA, A." + Const.AUTHOR_SURNAME + ", A." + Const.AUTHOR_PATRONYMIC + " FROM "
                + Const.BOOKS_TABLE + " B "
                + "INNER JOIN " + Const.AUTHOR_BOOKS_TABLE + " AB ON B." + Const.BOOKS_ID_B + "=AB." + Const.AUTHOR_BOOKS_ID_B
                + " INNER JOIN " + Const.AUTHOR_TABLE + " A ON A." + Const.AUTHOR_ID_A + "=AB." + Const.AUTHOR_BOOKS_ID_A
                + " INNER JOIN " + Const.INSTENCES_TABLE + " I ON B." + Const.BOOKS_ID_B + "=I." + Const.INSTENCES_ID_B
                + " WHERE UPPER(B."  + Const.BOOKS_NAME + ") = UPPER('" +  nameBook + "') "
                + "AND UPPER(A." + Const.AUTHOR_NAME + ") = UPPER('" + nameA + "') "
                + "AND UPPER(A." + Const.AUTHOR_SURNAME + ") = UPPER('" + nameS + "') "
                + "AND UPPER(A." + Const.AUTHOR_PATRONYMIC + ") = UPPER('" + nameP + "') "
                + "AND I." + Const.INSTENCES_CONDITION + " = 'В наличии' "
                ;

        System.out.println(select);
        try{
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }



    public ResultSet getResult(){
        ResultSet resSet = null;
        String select = "SELECT I."  + Const.INSTENCES_ID_I + ", B."  + Const.BOOKS_NAME +
                ", A." + Const.AUTHOR_NAME +  " || ' ' || A." + Const.AUTHOR_SURNAME + " || ' ' || A." + Const.AUTHOR_PATRONYMIC + " AS FIO," + "I." + Const.INSTENCES_CONDITION
                + ", D." + Const.DISTRIBUTION_DATE_GET + ", D." + Const.DISTRIBUTION_DATE_RETURN + " FROM "
                + Const.BOOKS_TABLE + " B "
                + "INNER JOIN " + Const.AUTHOR_BOOKS_TABLE + " AB ON B." + Const.BOOKS_ID_B + "=AB." + Const.AUTHOR_BOOKS_ID_B
                + " INNER JOIN " + Const.AUTHOR_TABLE + " A ON A." + Const.AUTHOR_ID_A + "=AB." + Const.AUTHOR_BOOKS_ID_A
                + " INNER JOIN " + Const.INSTENCES_TABLE + " I ON B." + Const.BOOKS_ID_B + "=I." + Const.INSTENCES_ID_B
                + " INNER JOIN " + Const.DISTRIBUTION_TABLE + " D ON D." + Const.DISTRIBUTION_ID_B + "=I." + Const.INSTENCES_ID_I
                ;

        System.out.println(select);
        try{
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }



    public ResultSet getCard(String idReader){
        ResultSet resSet = null;
        String select = "SELECT I."  + Const.INSTENCES_ID_I + ", B."  + Const.BOOKS_NAME +
                ", A." + Const.AUTHOR_NAME +  " || ' ' || A." + Const.AUTHOR_SURNAME + " || ' ' || A." + Const.AUTHOR_PATRONYMIC + " AS FIO, D."
                + Const.DISTRIBUTION_DATE_GET + ", D." + Const.DISTRIBUTION_DATE_RETURN + " FROM "
                + Const.BOOKS_TABLE + " B "
                + "INNER JOIN " + Const.AUTHOR_BOOKS_TABLE + " AB ON B." + Const.BOOKS_ID_B + "=AB." + Const.AUTHOR_BOOKS_ID_B
                + " INNER JOIN " + Const.AUTHOR_TABLE + " A ON A." + Const.AUTHOR_ID_A + "=AB." + Const.AUTHOR_BOOKS_ID_A
                + " INNER JOIN " + Const.INSTENCES_TABLE + " I ON B." + Const.BOOKS_ID_B + "=I." + Const.INSTENCES_ID_B
                + " INNER JOIN " + Const.DISTRIBUTION_TABLE + " D ON D." + Const.DISTRIBUTION_ID_B + "=I." + Const.INSTENCES_ID_I
                + " INNER JOIN " + Const.READER_TABLE + " R ON R." + Const.READER_ID_R + "=D." + Const.DISTRIBUTION_ID_A
                + " WHERE R." + Const.READER_ID_R + " = " + idReader
                ;

        System.out.println(select);
        try{
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }

    public ResultSet getTop(String dateStart, String dateEnd){
        ResultSet resSet = null;
        String select = "SELECT * FROM "
        + "(SELECT b." + Const.BOOKS_ID_B + ", VBRMAX.MAXIMUM, B." + Const.BOOKS_NAME
        + ", A." + Const.AUTHOR_SURNAME + "|| ' ' || A." + Const.AUTHOR_NAME + "|| ' ' || A." + Const.AUTHOR_PATRONYMIC + " AS FIO "
        + " FROM " + Const.BOOKS_TABLE + " B "
        + " INNER JOIN " + Const.AUTHOR_BOOKS_TABLE + " AB ON B." + Const.BOOKS_ID_B + "=AB." + Const.AUTHOR_BOOKS_ID_B
        + " INNER JOIN " + Const.AUTHOR_TABLE +  " A ON A." + Const.AUTHOR_ID_A + "=AB." + Const.AUTHOR_BOOKS_ID_A
        + " INNER JOIN "
        + "(SELECT b." + Const.BOOKS_ID_B + ", MAX(VBRCOUNT.КОЛИЧЕСТВО) MAXIMUM"
        + " FROM " + Const.BOOKS_TABLE + " B "
        + " INNER JOIN "
        + "(SELECT b." + Const.BOOKS_ID_B +", COUNT(" + Const.BOOKS_ID_B + ") AS КОЛИЧЕСТВО "
        + " FROM "
        + Const.BOOKS_TABLE + " B "
        + " INNER JOIN " + Const.AUTHOR_BOOKS_TABLE + " AB ON B." + Const.BOOKS_ID_B + "=AB." + Const.AUTHOR_BOOKS_ID_B
        + " INNER JOIN " + Const.AUTHOR_TABLE + " A ON A." + Const.AUTHOR_ID_A + "=AB." + Const.AUTHOR_BOOKS_ID_A
        + " INNER JOIN " + Const.INSTENCES_TABLE +  " I ON B." + Const.BOOKS_ID_B + "=I." + Const.INSTENCES_ID_B
        + " INNER JOIN " + Const.DISTRIBUTION_TABLE + " D ON D." + Const.DISTRIBUTION_ID_B + "=I." + Const.INSTENCES_ID_I
        + " WHERE d." + Const.DISTRIBUTION_DATE_GET + "> '" + dateStart
        + "' AND ( D." + Const.DISTRIBUTION_DATE_RETURN + "< '" + dateEnd + "' OR D."+ Const.DISTRIBUTION_DATE_RETURN + " IS NULL)"
        + " GROUP BY b." + Const.BOOKS_ID_B + ") VBRCOUNT"
        + " ON VBRCOUNT.id_b = b.id_b "
        + " GROUP BY b." + Const.BOOKS_ID_B
        + " ORDER BY MAXIMUM DESC) VBRMAX"
        + " ON VBRMAX.ID_B = B." + Const.BOOKS_ID_B
        + " ORDER BY VBRMAX.MAXIMUM DESC)"
        + " WHERE ROWNUM <4"
                ;

        System.out.println(select);
        try{
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;


    }

    public ResultSet getFormBook(String idBook){
        ResultSet resSet = null;
        String select = "SELECT B.ID_B, B.NAME, A.NAME || ' ' || A.SURNAME || ' ' || A.Patronymic AS FIO ,"
        + " B.CIPHER, B.TOPIC, B.PUBLISH, B.DATE_P, B.quality, "
        + " decode (D.Количество, null, 0, D.Количество) as GET "
        + " FROM "
        + " BOOKS B "
        + " INNER JOIN AUTHOR_BOOKS AB ON B.ID_B=AB.ID_B "
        + " INNER JOIN AUTHOR A ON A.ID_A=AB.ID_A "
        + " INNER JOIN INSTENCES I ON B.ID_B=I.ID_B "
        + " full JOIN (select id_i, count(id_i) as Количество from distribution where date_return is null group by id_i) D "
        + " ON D.ID_I=I.ID_I "
        + " WHERE B.ID_B=" + idBook
                ;

        System.out.println(select);
        try{
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }

    public void exBook1(String id_i){
        String update = "UPDATE INSTENCES SET CONDITION = 'Нет в наличии' where id_i = " + id_i;

        System.out.println(update);
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(update);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void exBook2(String id_i, String id_r){
        String update ="INSERT INTO DISTRIBUTION (ID_R, ID_I, DATE_GET)" +
                "VALUES (" + id_r + ", " + id_i + ", sysdate)";

        System.out.println(update);
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(update);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void retBook1(String id_i, String id_r){
        String update = "UPDATE DISTRIBUTION SET DATE_RETURN = sysdate where ID_R = " + id_r +
                "AND id_i = " + id_i;

        System.out.println(update);
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(update);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void retBook2(String id_i){
        String update = "UPDATE INSTENCES SET CONDITION = 'В наличии' where id_i=" + id_i;

        System.out.println(update);
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(update);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public ResultSet getHolder(String idReader){
        ResultSet resSet = null;
        String select = "SELECT R.ID_R,  R.SURNAME || ' ' || R.NAME || ' ' || R.PATRONYMIC AS FIO, R.Num_Ticket, R.telephone FROM"
        + " BOOKS B "
        + " INNER JOIN INSTENCES I ON B.ID_B=I.ID_B "
                + " INNER JOIN DISTRIBUTION DIS ON DIS.ID_I=I.ID_I "
                + " INNER JOIN READERS R ON R.ID_R=DIS.ID_R "
                + " WHERE i.id_i =" +  idReader
                + " AND dis.date_return IS NULL "
                ;

        System.out.println(select);
        try{
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }

    public ResultSet getMask(String bookName, String bookTopic, String bookPublish, String bookAuthorName
                             , String bookPatronymic, String bookSurname){
        ResultSet resSet = null;
        String select = "SELECT b.name, b.topic, b.publish, a.surname, a.name, a.patronymic FROM BOOKS B "
        + " INNER JOIN AUTHOR_BOOKS AB ON B.ID_B=AB.ID_B "
        + " INNER JOIN AUTHOR A ON A.ID_A=AB.ID_A "
        + " WHERE UPPER(b.NAME) LIKE UPPER('%" + bookName + "%') "
        + " and UPPER(b.TOPIC) LIKE UPPER('%" + bookTopic + "%') "
        + " and UPPER(b.PUBLISH) LIKE UPPER('%" + bookPublish + "%') "
        + " and UPPER(a.NAME) LIKE UPPER('%" + bookAuthorName + "%') "
        + " and UPPER(a.surname) LIKE UPPER('%" + bookSurname + "%') "
        + " and UPPER(a.patronymic) LIKE UPPER('%" + bookPatronymic + "%')"
                ;

        System.out.println(select);
        try{
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }
}
