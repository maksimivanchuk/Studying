public class Student implements Comparable<Student> {
    private String name;
    private int course, ball;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", course=" + course +
                ", ball=" + ball +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getGroup() {
        return ball;
    }

    public void setGroup(int ball) {
        this.ball = ball;
    }

    public Student(String name, int course, int ball) {

        this.name = name;
        this.course = course;
        this.ball = ball;
    }

    @Override
    public int compareTo(Student o) {
        if (course == o.course){
            if (ball == o.ball) return name.compareTo(o.name);
            return ball - o.ball;
        }
        return course - o.course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;
        if (course != student.course) return false;
        if (ball != student.ball) return false;
        return name.equals(student.name);
    }

}