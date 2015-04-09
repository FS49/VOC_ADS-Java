package kapitel_3.work.junit;

import static org.junit.Assert.*;

import org.junit.runners.MethodSorters;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import kapitel_3.work.IFIterator;
import kapitel_3.work.SList;
import kapitel_3.work.tests.Student;
import kapitel_3.work.tests.StudentKeys;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SListTest {
    static SList studentList;
    static Student[] students;
    static int einsteinIndex;
    static int index;
    
    @BeforeClass
    public static void initObjects() {
        index = 0;
        
        studentList = new SList();
        
        students = new Student[3];
    }
    
    @Test
    public void test1_Prepend() {
        students[index] = new Student("Volker", "Christian", "MTD0100001");
        studentList.prepend(students[index]);
        index++;
        
        students[index] = new Student("Albert", "Einstein", "MTD0100002");
        studentList.prepend(students[index]);
        einsteinIndex = index;
        index++;
        
        students[index] = new Student("Wolfgang", "Ambros", "MTD0100003");
        studentList.prepend(students[index]);
        
        IFIterator it = studentList.iterator();
        
        while(it.hasNext()) {
            assertSame("should be same", it.next(), students[index--]);
        }
        
        assertEquals("should be -1", -1, index);
    }

    @Test
    public void test2_Search() {
        StudentKeys.SurNameKey nameKey = new StudentKeys.SurNameKey("Einstein");
        
        Student einsteinStudent = (Student) studentList.search(nameKey);
        
        assertSame("should be Einstein", einsteinStudent, students[einsteinIndex]);
    }

    @Test
    public void test3_Remove() {
        studentList.remove(new Object());
        
        IFIterator it = studentList.iterator();
        
        assertSame("should be same", students[2], it.next());
        
        studentList.remove(students[einsteinIndex]);

        StudentKeys.SurNameKey nameKey = new StudentKeys.SurNameKey("Einstein");
        
        Student einsteinStudent = (Student) studentList.search(nameKey);
        
        assertNull("should not be Einstein", einsteinStudent);
    }

    @Test
    public void test4_Iterator() {
//        fail("Not yet implemented");
    }

}
