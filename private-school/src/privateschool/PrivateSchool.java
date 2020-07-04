package privateschool;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import dao.AssignmentDao;
import dao.AssignmentPerCourseDao;
import dao.CourseDao;
import dao.GradeDao;
import dao.StudentDao;
import dao.StudentPerCourseDao;
import dao.TrainerDao;
import dao.TrainerPerCourseDao;
import entities.Assignment;
import entities.Course;
import entities.Grade;
import entities.Student;
import entities.Trainer;

public class PrivateSchool {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int choice;
		boolean showRegisterMenu = true;

		do {
			printOptionsMenu(showRegisterMenu);
			System.out.print("\nChoose an option or 0 to exit: ");
			choice = sc.nextInt();

			if (showRegisterMenu) {
				switch (choice) {
				case 1:
					addStudent();
					break;
				case 2:
					addTrainer();
					break;
				case 3:
					addCourse();
					break;
				case 4:
					assignStudentToCourse();
					break;
				case 5:
					addAssignmentToCourse();
					break;
				case 6:
					addGradeToStrudent();
					break;
				default:
					showRegisterMenu = false;
					break;
				}
			} else {
				switch (choice) {
				case 1:
					System.out.println("\n--- Students ---");
					new StudentDao().findAll().forEach(System.out::println);
					break;
				case 2:
					System.out.println("\n--- Trainers ---");
					new TrainerDao().findAll().forEach(System.out::println);
					break;
				case 3:
					System.out.println("\n--- Assignments ---");
					new AssignmentDao().findAll().forEach(System.out::println);
					break;
				case 4:
					System.out.println("\n--- Courses ---");
					new CourseDao().findAll().forEach(System.out::println);
					break;
				case 5:
					System.out.println("\n--- Students Per Course ---");
					List<Student> students = studentsPerCourse();
					students.forEach(System.out::println);
					break;
				case 6:
					System.out.println("\n--- Trainers Per Course ---");
					List<Trainer> trainers = trainersPerCourse();
					trainers.forEach(System.out::println);
					break;
				case 7:
					System.out.println("\n--- Assignments Per Course ---");
					List<Assignment> assignments = assignmentsPerCourse();
					assignments.forEach(System.out::println);
					break;
				case 8:
					System.out.println("\n--- Assignment Per Student Per Course ---");
					List<Grade> grades = assignmentPerStudentPerCourse();
					grades.forEach(System.out::println);
					break;
				case 9:
					new StudentPerCourseDao().findStudentsRegisteredToMoreThanOneCourse().forEach(System.out::println);
					break;
				case 10:
					showRegisterMenu = true;
					break;
				}
			}

		} while (choice != 0);

		sc.close();
		System.out.println("Bye!!!");
	}

	private static void printOptionsMenu(boolean showRegisterMenu) {

		System.out.println();
		if (showRegisterMenu) {
			System.out.println("1. Add student.");
			System.out.println("2. Add trainer.");
			System.out.println("3. Add course.");
			System.out.println("4. Assign student to course.");
			System.out.println("5. Add assignment to course.");
			System.out.println("6. Add grade to Student.");
			System.out.println("7. Go to list options.");
		} else {
			System.out.println();
			System.out.println("1. - List all students!");
			System.out.println("2. - List all trainers!");
			System.out.println("3. - List all assignments!");
			System.out.println("4. - List all courses!");
			System.out.println("5. - List all students per course!");
			System.out.println("6. - List all trainers per course!");
			System.out.println("7. - List all assignments per course!");
			System.out.println("8. - List all assignments per student per course!");
			System.out.println("9. - List all students registered to more than one course!");
			System.out.println("10. - Go to register options!");
		}
	}

	public static List<Student> studentsPerCourse() {

		int courseId = getCourse();
		return new StudentPerCourseDao().findById(courseId);
	}

	public static List<Trainer> trainersPerCourse() {

		int courseId = getCourse();
		return new TrainerPerCourseDao().findById(courseId);
	}

	public static List<Assignment> assignmentsPerCourse() {

		int courseId = getCourse();
		return new AssignmentPerCourseDao().findById(courseId);
	}

	public static List<Grade> assignmentPerStudentPerCourse() {
		int courseId = getCourse();
		int studentId = getStudent();
		return new GradeDao().findById(studentId, courseId);
	}

	public static int getCourse() {
		Scanner sc = new Scanner(System.in);
		List<Course> courses = new CourseDao().findAll();
		courses.forEach(System.out::println);
		int courseId = 0;

		do {
			System.out.print("\nChoose course id: ");
			try {
				courseId = sc.nextInt();
				break;
			} catch (Exception e) {
				System.out.println("Wrong Input!");
				sc.next();
			}
		} while (true);

		return courseId;
	}

	public static int getStudent() {
		Scanner sc = new Scanner(System.in);
		List<Student> students = new StudentDao().findAll();
		System.out.println();
		students.forEach(System.out::println);
		int studentId = 0;

		do {
			System.out.print("\nChoose student id: ");
			try {
				studentId = sc.nextInt();
				break;
			} catch (Exception e) {
				System.out.println("Wrong Input!");
				sc.next();
			}
		} while (true);

		return studentId;
	}

	public static void addStudent() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter student first name: ");
		String fname = sc.next();
		System.out.print("Enter student last name: ");
		String lname = sc.next();

		LocalDate dob;
		do {
			System.out.print("Enter date of birth: ");
			try {
				dob = LocalDate.parse(sc.next());
				break;
			} catch (DateTimeParseException e) {
				System.out.println("Wrong date format!");
			}
		} while (true);

		double tuitionFee;
		do {
			System.out.print("\nEnter tuition fee: ");
			try {
				tuitionFee = sc.nextDouble();
				break;
			} catch (Exception e) {
				System.out.println("Wrong Input!");
				sc.next();
			}
		} while (true);

		Student s = new Student(fname, lname, dob, tuitionFee);
		new StudentDao().create(s);
	}

	public static void addTrainer() {

		Scanner sc = new Scanner(System.in);
		System.out.print("Enter trainer first name: ");
		String fname = sc.next();
		System.out.print("Enter trainer last name: ");
		String lname = sc.next();
		System.out.print("Enter trainer subject: ");
		String subject = sc.next();

		Trainer t = new Trainer(fname, lname, subject);
		new TrainerDao().create(t);
	}

	public static void addCourse() {

		Scanner sc = new Scanner(System.in);
		System.out.print("Enter course title: ");
		String title = sc.next();
		System.out.print("Enter course stream: ");
		String stream = sc.next();
		System.out.print("Enter course type: ");
		String type = sc.next();

		LocalDate startDate;
		do {
			System.out.print("Enter course starting date: ");
			try {
				startDate = LocalDate.parse(sc.next());
				break;
			} catch (DateTimeParseException e) {
				System.out.println("Wrong date format!");
			}
		} while (true);

		LocalDate endDate;
		do {
			System.out.print("Enter course ending date: ");
			try {
				endDate = LocalDate.parse(sc.next());
				break;
			} catch (DateTimeParseException e) {
				System.out.println("Wrong date format!");
			}
		} while (true);

		Course c = new Course(title, stream, type, startDate, endDate);
		new CourseDao().create(c);
	}

	public static void assignStudentToCourse() {
		int studentId = getStudent();
		int courseId = getCourse();

		new StudentPerCourseDao().create(studentId, courseId);
	}

	public static void addAssignmentToCourse() {

		Scanner sc = new Scanner(System.in);
		System.out.print("Enter assignment title: ");
		String title = sc.next();
		System.out.print("Enter assignment description: ");
		String description = sc.next();

		LocalDate submitDate = null;
		boolean wrongDateFormat;
		do {
			System.out.print("Enter assignment submit date: ");
			try {
				submitDate = LocalDate.parse(sc.next());
				wrongDateFormat = false;
			} catch (DateTimeParseException e) {
				System.out.println("Wrong date format!");
				wrongDateFormat = true;
			}
		} while (wrongDateFormat);

		System.out.print("Enter assignment max oral grade: ");
		int oralMax = sc.nextInt();

		System.out.print("Enter assignment max total grade: ");
		int totalMax = sc.nextInt();

		int courseId = getCourse();

		Assignment a = new Assignment(title, description, submitDate, oralMax, totalMax, courseId);
		new AssignmentDao().create(a);
	}

	public static void addGradeToStrudent() {
		Scanner sc = new Scanner(System.in);
		int studentId = getStudent();
		int courseId = 0;
		List<Integer> courseIds = new StudentPerCourseDao().findCourseFromStudent(studentId);

		if (courseIds.size() > 1) {
			for (Integer i : courseIds) {
				Course c = new CourseDao().findById(i);
				System.out.println(c);
			}

			do {
				System.out.print("\nChoose course id: ");
				try {
					courseId = sc.nextInt();
					if (courseId > 0 && courseId <= courseIds.size()) {
						break;
					} else {
						System.out.println("Wrong Input!");
					}
				} catch (Exception e) {
					System.out.println("Wrong Input!");
					sc.next();
				}
			} while (true);

		} else {
			courseId = courseIds.get(0);
		}

		List<Assignment> assignments = new AssignmentPerCourseDao().findById(courseId);
		for (Assignment a : assignments) {
			System.out.println(a);
		}

		int assignmentId;
		do {
			System.out.print("\nChoose assignment id: ");
			try {
				assignmentId = sc.nextInt();
				break;
			} catch (Exception e) {
				System.out.println("Wrong Input!");
				sc.next();
			}
		} while (true);

		int oralMark = 0;
		do {
			System.out.print("\nEnter oral mark: ");
			try {
				oralMark = sc.nextInt();
				break;
			} catch (Exception e) {
				System.out.println("Wrong Input!");
				sc.next();
			}
		} while (true);

		int totalMark = 0;
		do {
			System.out.print("\nEnter total mark: ");
			try {
				totalMark = sc.nextInt();
				break;
			} catch (Exception e) {
				System.out.println("Wrong Input!");
				sc.next();
			}
		} while (true);

		new GradeDao().create(new Grade(oralMark, totalMark, studentId, assignmentId));
		System.out.println("--- Assignment added ! ---");
	}
}
