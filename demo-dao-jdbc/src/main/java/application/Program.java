package program;

import db.DB;
import model.entities.Department;

public class Application {
    public static void main(String[] args) {
        Department dep = new Department(1, "Books");
        System.out.println(dep);
    }
}
