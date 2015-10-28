package kapitel_3.work;

public abstract class Exercise {
    static String uePackage = null;
    
    public Exercise() {}
    
    public static void registerExercise(String uePackage) {
        Exercise.uePackage = uePackage;
    }
    
    public static void registerExercise(@SuppressWarnings("rawtypes") Class exerciseClass) {
        Package exercisePackage = exerciseClass.getPackage();
        Exercise.uePackage = exercisePackage.getName();
    }
    
    @SuppressWarnings("unchecked")
    protected static <T> T newInstance(String className) {
        T object = null;
        
        try {
            Class<?> myClass = null;
            myClass = Class.forName(uePackage + "." + className);
            object = (T) myClass.newInstance();
        } catch (InstantiationException e) {
            System.out.println("Class " + uePackage + "/" + className + " can not be instantiated");
            e.printStackTrace();
            System.exit(-1);
        } catch (IllegalAccessException e) {
            System.out.println("Class " + uePackage + "/" + className + " is not accessable");
            e.printStackTrace();
            System.exit(-1);
        } catch (ClassNotFoundException e) {
            System.out.println("Class " + uePackage + "/" + className + " could not be found");
            e.printStackTrace();
            System.exit(-1);
        }
        
        return object;
    }
    
//    public abstract void test();
    
    public static void main() {
//        test();
    }
}
