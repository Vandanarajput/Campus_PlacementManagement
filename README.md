# 🎓 Campus Placement Management System

A full-stack web application to manage and streamline the college placement process for students, administrators, and recruiters.

## 🛠️ Technologies Used

- **Backend:** Java, Spring Boot, Hibernate
- **Frontend:** Thymeleaf, HTML, CSS, Bootstrap
- **Database:** MySQL
- **Build Tool:** Maven
- **Validation:** Jakarta Bean Validation

## 👥 Users & Roles

1. **Admin:**
   - Manage students and companies
   - Post job opportunities
   - View all job applications

2. **Student:**
   - Register/login
   - Apply for jobs
   - Upload resume and skills
   - Track application status
   - View/edit profile

3. **Recruiter (Optional/for future enhancement):**
   - Post jobs
   - Review applicants

## 📂 Project Structure

```
PlacementManagementSystem/
│
├── src/main/java
│   ├── com.PlacementManagementSystem.Placement
│       ├── controller/
│       ├── model/
│       ├── repository/
│       ├── service/
│       └── config/
│
├── src/main/resources/
│   ├── templates/
│   │   ├── users/
│   │   └── userdashbored/
│   ├── static/
│   └── application.properties
│
├── pom.xml
└── README.md
```

## 🚀 Features

- ✅ User Registration & Login (with role-based session management)
- ✅ Resume upload and review step
- ✅ Job application flow with confirmation step
- ✅ Admin panel to manage jobs and users
- ✅ View applied jobs and application status
- ✅ Skill management for students

## 🖥️ Screens

- Register / Login Forms
- Job Listings
- Job Application Form (2-step)
- Student Dashboard
- Admin Dashboard

## ⚙️ Setup Instructions

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/your-username/placement-management-system.git
   ```

2. **Open in IDE** (IntelliJ or Eclipse recommended)

3. **Database Setup:**
   - Create a MySQL database:  
     ```sql
     CREATE DATABASE vandana;

     ```
   - Update `application.properties` with DB credentials.
   
   - spring.datasource.url=jdbc:mysql://localhost:3306/vandana
   - spring.datasource.username=root
   - spring.datasource.password=root
   - spring.jpa.hibernate.ddl-auto=update
   - spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
   - spring.servlet.multipart.location=D:/uploads/resumes


4. **Run the Application:**
   - Use your IDE or:
     ```bash
     mvn spring-boot:run
     ```

5. **Access in Browser:**
   ```
   http://localhost:8080/
   ```

## 📷 Sample Credentials

- **Admin:**  
  Email: `admin@mail.com`  
  Password: `admin123`

- **Student:**  
  Register as new user

## 📝 TODO (For Future Enhancements)

- Recruiter role and panel
- Email notifications
- PDF resume parsing
- Interview scheduling module
- Job recommendations based on skills

## 📌 Contributing

Feel free to fork this repository and submit pull requests. For major changes, open an issue first.

## 🙋‍♀️ Author

- 👩‍💻 Vandana (Vandu)  
  MCA Final Year Student  
  🌐 [LinkedIn](https://www.linkedin.com/in/vandana-rajput-213160277/) | 📧 vandanakashyap674@gmail.com

---

**⭐ Don’t forget to star the repo if you find it helpful!**
