# Simple JDBC API

Simple JDBC API provides the abstraction layer on JDBC API. It simplifies the use of JDBC and helps to avoid common errors. It removes lots of boiler-plate code.

## Getting Started

If you want to use this API you need to add [jdbc-template.jar](https://github.com/jayeshtajane/simple-jdbc) in your project class path.
To do any JDBC operation you must need the object of JdbcTemplate.

### Creating JdbcTemplate

```
String driver="com.mysql.jdbc.Driver";
String url="jdbc:mysql://localhost:3306/test";
String username="root";
password="tiger";
JdbcTemplate template = new SimpleJdbcTemplate(driver,url,username,tiger);
```

Insted of passing one by one value in constructor you can diractly pass the java.util.Properties class object but that object must have following keys - 
1. jdbc.driver
2. jdbc.url
3. jdbc.username
4. jdbc.password

```
Properties p = new Properties();
p.put("jdbc.driver","com.mysql.jdbc.Driver");
p.put("jdbc.url","jdbc:mysql://localhost:3306/test"):
p.put("jdbc.username","root");
p.put("jdbc.password","tiger"):
JdbcTemplate template = new SimpleJdbcTemplate(p);
```

You can also create JdbcTemplate object by using properties file. Instead of passing properties class object you can pass the path of your properties file. But make sure your properties file must be in your project classpath.

First create "app.properties" file and put the following keys.
```
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/test
jdbc.username=root
jdbc.password=tiger
```

Now create JdbcTemplate object by passing the properties file name.
```
JdbcTemplate template = new SimpleJdbcTemplate("app.properties");
```


**Note:- I performing all the operations on the following table**
```
create table emptab(
  eid number, 
  ename varchar(20), 
  esal number(10,2)
);
```

### Performing INSERT operations
* Step 1 - Write insert query
* Step 2 - Create Query object using template we just created above.
* Step 3 - execute the query

```
// SQL query
String sql="insert into emptab(eid,ename,esal) values(?,?,?)";
int eid = 101;
String ename="ABC";
double esal = 20000;
// In createQuery() pass the above query and values that you want replace with question mark of query respectively.
// It will return the Query object
Query query=template.createQuery(sql,eid,ename,esal);
// Execute the query
// It will return number of rows affected in database.
int num = query.update();
```

### Performing UPDATE operation
* Step 1 - Write update query
* Step 2 - Create Query object using template.
* Step 3 - execute the query

```
// SQL query
String sql="update emptab set ename=?,esal=? where eid=?";
int eid = 101;
String ename="XYZ";
double esal = 25000;
// In createQuery() pass the above query and values that you want replace with question mark of query respectively.
// It will return the Query object
Query query=template.createQuery(sql,ename,esal,eid);
// Execute the query
// It will return number of rows affected in database.
int num = query.update();
```

### Performing DELETE operation
* Step 1 - Write delete query
* Step 2 - Create Query object using template.
* Step 3 - execute the query

```
// SQL query
String sql="delete from emptab where eid=?";
int eid = 101;
// In createQuery() pass the above query and values that you want replace with question mark of query respectively.
// It will return the Query object
Query query=template.createQuery(sql,eid);
// Execute the query
// It will return number of rows affected in database.
int num = query.update();
```

### Performing SELECT operation
To perform the select operation you need to write follwing files.

* Model class - To store the data of row which is fetched from ResultSet.

```
// Employee.java

public class Employee {
  private int eid;
  private String ename;
  private double esal;
  // default constructor
  // setters and getters
  // toString
}
```

* Mapper class - To set the data to the model class object.

```
// EmployeeRowMapper.java

import java.sql.ResultSet;
import java.sql.SQLException;
import com.jayeshtajane.simplejdbc.mapper.RowMapper;

public class EmployeeRowMapper implements RowMapper <Employee> {
	@Override
	public Employee mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
		Employee e = new Employee();
		e.setEmpId(resultSet.getInt("eid")); 
		e.setEmpName(resultSet.getString("ename"));
		e.setEmpSal(resultSet.getDouble("esal"));
		return e;
	}
}
```

Now you are able to perform select operation.
* Step 1 - Write select query
* Step 2 - Create Query object using template.
* Step 3 - execute the query

**Note:- If your query is fetching multiple rows then you should go for load() method otherwise you can use get() method.**

Using get() method
```
// SQL query
String sql="select * from emptab where eid=?";
int eid = 101;
Query query=template.createQuery(sql,eid);
// Extecute query by calling get() method. In get() method pass the object of RowMapper
// It return the T class object
Employee e = query.get(new EmployeeRowMapper());
System.out.println(e);
```

Using load() method
```
// SQL query
String sql="select * from emptab";
Query query=template.createQuery(sql);
// Extecute query by calling load() method. In load() method pass the object of RowMapper
// It return the List<T>
List<Employee> e = query.load(new EmployeeRowMapper());
System.out.println(e);
```

## Referance links

* [Documentation](http://www.dropwizard.io/1.0.2/docs/) - Documentation
* [GitHub](https://github.com/jayeshtajane/simple-jdbc) - GitHub repository

## Authors

* **Jayesh Tajane**
