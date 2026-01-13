// START FROM LINE 494

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;


class Box {
    int value;
}


// Immutable object example
class User {

    final int id;
    String name;

    // Constructor name MUST match class name
    User() {                 // default behavior
        this(0, "Unknown");  // constructor chaining
    }

    User(int id) {
        this(id, "Unknown");
    }

    User(String name) {
        this(0, name);
    }

    User(int id, String name) {
        this.id = id;
        this.name = name;
    }
}


class Parent {

    int x = 10;

    final void show() {
        System.out.println("Parent");
    }
}


class Child extends Parent {

    int x = 20;

    void thisShow() {
        System.out.println("Child.thisShow()");
        System.out.println("this.x: " + this.x);
    }

    void superShow() {
        System.out.println("Child.superShow()");
        System.out.println("super.x: " + super.x);
    }

    // void show() // cannot override final methods
}


// final class cannot be extended
final class Utility {
    void util() {
        System.out.println("Utility class");
    }
}
// class ExtendedUtility extends Utility {} // cannot extend final class


class OverloadDemo {

    static void show(int x) {
        System.out.println("int");
    }

    static void show(double x) {
        System.out.println("double");
    }

    static void show(Integer x) {
        System.out.println("Integer");
    }
}


// STATIC MEMORY EXAMPLE
class Counter {

    static int count = 0;
    int sub_count = 0;

    void incrementCount() {
        count++;
        sub_count++;
        // this.sub_count++; // also works
        // java automatically adds 'this.' for instance variables accessed inside instance methods
    }

    static void staticIncrementCount() {
        // .this is NOT allowed in static methods
        count++;
        // sub_count++; // ERROR: non-static variable sub_count cannot be referenced from a static context

        // staticIncrementCount() method exists in static memory, not in heap
        // so it cannot access instance variables (which exist in heap)
    }
}


class BlockDemo {

    static {
        System.out.println("Static block inside BlockDemo class");
    }

    {
        System.out.println("Instance block inside BlockDemo class");
    }

    BlockDemo() {
        System.out.println("Constructor inside BlockDemo class");
    }
}


class StaticOrder {
    static int x = 10;
    int y = 5;

    static {
        x = 20;
        // y = 15; // cannot access coz static block is only executed once at class loading
        // before any instance is created
        // before y even exists in heap memory of some object
    }

    static {
        x = 30;
    }

    {
        System.out.println("Instance block inside StaticOrder class");
        System.out.println("static x in instance block: " + x);
        System.out.println("instance y in instance block: " + this.y);

    }
}


// static and polymorphism
class A {
    static void show() {
        System.out.println("A");
    }
    void non_static_show() {
        System.out.println("Non-static A");
    }
}
class B extends A {
    static void show() {
        System.out.println("B");
    }
    void non_static_show() {
        System.out.println("Non-static B");
    }
    void only_b_show() {
        System.out.println("Only B show");
    }
}


// Java does NOT allow top-level static classes
// But it allows static nested classes
class Outer {

    static int x = 10;
    int y = 20;

    static class Inner {
        void show() {
            System.out.println(x);  // static access ALLOWED
            // System.out.println(y); this.y NOT ALLOWED
        }
    }
    // Static classes cannot access instance variables
}


class PrivateDemo {

    int default_ryan = 10; // when no keyword is specified, it means "default" access modifier
    // default variables/methods are accessible within the same package. They're also called package-private

    private int private_ryan = 10;
    // private access modifier exists for encapsulation and security
    // only accessible within the same class (not even sub-class can access it)    

    private void privateShow() {
        System.out.println("privateShow() functtion called");
    }

    void defaultShow() {
        System.out.println("defaultShow() function calls privateShow()");
        privateShow();
    }
}


class ProtectedDemo {

    protected int protected_ryan = 10;

    protected void protectedShow() {
        System.out.println("protectedShow() function called");
    }
    // protected works VIA inheritence
    // sub-class even in different package can access it
    // non-sub-class outside this package cannot access it
}


class ThisDemo {

    int this_x = 10;

    void incrementWithoutThis(int this_x) {
        // argument gives copy-of-value which is stored in int variable 'this_x'
        // so this_x is NOT the same as this.this_x
        this_x++;
        System.out.println("this_x in incrementWithoutThis: " + this_x);
    }

    void incrementWithThis(int this_x) {
        this.this_x++;
    }
}

// INTERFACES
interface Flyable {
    int MAX_SPEED = 900; // public static final (always)
    void fly();          // public abstract (always)
    // at compile time it becomes:-
    // public static final int MAX_SPEED = 900;
    // public abstract void fly();

}
class Bird implements Flyable {

    int speed;

    public void fly() {
        System.out.println("Bird flying");
    }
}


interface AnimalInterface {
    void makeSound();
}
class Dog implements AnimalInterface {
    public void makeSound() {
        System.out.println("Dog barking");
    }
}
class Cat implements AnimalInterface {
    public void makeSound() {
        System.out.println("Cat meowing");
    }
}


// class Animal {
//     void eat() {
//         System.out.println("Eating");
//     }
// }
// class Robot {
//     void recharge() {
//         System.out.println("Recharging");
//     }
// }


// class RobotDog extends Animal, Robot { } // NOT ALLOWED, a sub-class can only extend one parent class
// so break 'capabilities of a RobotDog into interfaces:-


interface Animal {
    void eat();
}
interface Robot {
    void recharge();
}
class RobotDog implements Animal, Robot {
    public void eat() {
        System.out.println("RobotDog is eating");
    }
    public void recharge() {
        System.out.println("RobotDog is echarging");
    }
    public void bark() {
        System.out.println("RobotDog is barking");
    }
}
// RobotDog signed a contract to implement both Animal and Robot


// ENCAPSULATION + getters/setters
class StudentRecord {
    private final String name;
    private double marks = 0.0;
    private char grade = 'F';

    StudentRecord(String name) {
        this.name = name;
    }
    // setter
    void setMarks(Number score) {
        double s = score.doubleValue();

        if (s < 0 || s > 100) {
            return;
        }

        this.marks = s;

        this.grade = switch ((int) s / 10) {
            case 10 -> 'S';
            case 9  -> 'A';
            case 8  -> 'B';
            case 7  -> 'C';
            case 6  -> 'D';
            default -> 'F';
        };
    }
    // getter
    double getMarks() {
        return marks;
    }
    // getter
    char getGrade() {
        return grade;
    }

    record Result(double marks, char grade) {}
    // getter
    Result getResult() {
        return new Result(marks, grade);
    }
    // getter
    String getName() {
        return name;
    }
}
// This is what 'record' compiles to:-

// final class Result {
//     private final double marks;
//     private final char grade;

//     public Result(double marks, char grade) {
//         this.marks = marks;
//         this.grade = grade;
//     }

//     public double marks() {
//         return marks;
//     }

//     public char grade() {
//         return grade;
//     }
// }


// -------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------


public class hello {

    static void methodA() {
        methodB();
        System.out.println("A ends");
    }

    static void methodB() {
        methodC();
        System.out.println("B ends");
    }

    static void methodC() {
        System.out.println("C ends");
    }

    static int change(int x) {
        x = 100;
        return x;
    }

    static void changeBox(Box b) {
        b.value = 100;
    }

    static Box returnBox(Box b) {
        b.value = 400;
        return b;
    }

    static void recurse(int n) {
        if (n == 0) return;
        System.out.println(n);
        recurse(n - 1);
    }

    static void recurse2(int n) {
        if (n == 0) return;
        recurse2(n - 1);
        System.out.println(n);
    }

    static int f() {
        System.out.println("f()");
        return 5;
    }

    static int g() {
        System.out.println("g()");
        return 10;
    }

    static void doubleTheArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] *= 2;
        }
    }

    static int[] reset(int[] x) {
        // 1. x receives a copy of reference of arr_a array (it points to arr_a array in heap)
        x = new int[]{7, 7, 7};
        // 2. x points to new array in heap, arr_a still points to {10, 2, 3}
        return x;
        // 3. we return reference of new array in heap
        // reassignment does NOT change original array arr_a
    }

    // interface demo
    static void playSound(AnimalInterface a) {
        a.makeSound();
    }

    static <T> void genericPrint(T value) {
        System.out.println(value);
    }
    // Type erasure: at runtime, it becomes this:-

    // static void genericPrint(Object value) {
    //     System.out.println(value);
    // }

    // UPPER-BOUND (lower that what is mentioned)
    static void upperBoundSum(List<? extends Number> list) {
        System.out.println("upperBoundSum()");
        for (Number n : list) { // go with the UPPER-MOST to cover itself and all subclasses
            System.out.println(n);
        }
    }
    // VALID:- any subclasses of Number or itself
    // List<Integer>
    // List<Number>
    // List<Double>
    // List<Long>

    // LOWER-BOUND (higher that what is mentioned)
    static void lowerBoundSum(List<? super Number> list) {
        System.out.println("lowerBoundSum()");
        for (Object n : list) { // go with the UPPER-MOST to cover itself and all subclasses
            System.out.println(n);
        }
    }
    // VALID:- any superclasses of Number or itself
    // List<Number>
    // List<Object>

    public static void main(String[] args) {

        // Variable Declaration
        String a = "java";
        String b = "java";
        // String Pool: looks for 'java' in the pool
        // If found, reference is assigned to both a and b
        // If not found, new memory is allocated in the pool
        String c = new String("java");
        String d = new String("java");
        // New memory is allocated in the heap for c
        String name = "Krishiv";
        int age = 20;
        double score = 93.45678;

        int num1 = 10;

        System.out.print("Hello");
        System.out.print(" ");
        System.out.println("World");
        System.out.println("Next Line");

        // F-strings
        System.out.printf("Name: %s%n", name); // equivalent to print, not println
        System.out.printf("Age: %d%n", age);
        System.out.printf("Score: %.2f%n", score);

        // Comparing Strings
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println("a == b : " + (a == b)); // compares references
        System.out.println("a == c : " + (a == c));
        System.out.println("c == d : " + (c == d));
        // coz c and d point to different memory locations in HEAP
        System.out.println("a.equals(b) : " + a.equals(b)); // compares values
        System.out.println("a.equals(c) : " + a.equals(c));

        // Incrementing
        System.out.println("num++");
        int num2 = num1++; // returrns num1 BEFORE incrementing
        // LHS first, then RHS
        System.out.println("num2: " + num2);
        System.out.println("num1: " + num1);

        num1 = 10;
        num2 = 0;

        // Incrementing
        System.out.println("++num");
        num2 = ++num1; // returns num1 AFTER incrementing 
        // RHS first, then LHS
        System.out.println("num2: " + num2);
        System.out.println("num1: " + num1);

        // Decrementing
        System.out.println("num--");
        int num3 = num2--; // returns num2 BEFORE decrementing
        // num2 is now 9
        System.out.println("num3: " + num3);
        System.out.println("num2: " + num2);

        int x = 5;
        int y = 10;
        String res = (x > y) ? "true" : "false";
        System.out.println("Result: " + res);
        // check type of res variable
        System.out.println("Type of res: " + res.getClass().getSimpleName());

        boolean res2 = x > y; // or (x > y)
        System.out.println("Result: " + res2);
        // check type of res2 variable // cant do this apparently
        if (!res2) {
            System.out.println("bool res2 is False");
        }
        else {
            System.out.println("bool res2 is True");
        }

        boolean bool_a = true;
        boolean bool_b = false;
        System.out.println("bool_a && bool_b: " + (bool_a && bool_b));
        System.out.println("bool_a || bool_b: " + (bool_a || bool_b));

        // StringBuilder vs StringBuffer
        // StringBuilder not thread-safe, faster, non-synchronized
        // StringBuffer is thread-safe, slower, synchronized

        StringBuilder sb = new StringBuilder("Hello");
        sb.append(" World");
        System.out.println(sb);

        StringBuffer sbf = new StringBuffer("Hello");
        sbf.append(" World");
        System.out.println(sbf);

        // All operations possible on Stringbuilder
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Java");
        sb2.append(" Programming");
        System.out.println(sb2);
        sb2.insert(5, "Language ");
        System.out.println(sb2);
        sb2.replace(5, 14, "Platform "); // end index exclusive
        System.out.println(sb2);
        sb2.delete(5, 14); // end index exclusive
        System.out.println(sb2);
        sb2.reverse();
        System.out.println(sb2);
        sb2.reverse(); // to get original string back
        System.out.println(sb2);
        // Rare operations on StringBuilder
        System.out.println("Character at index 2: " + sb2.charAt(2));
        System.out.println("Length of sb2: " + sb2.length());
        System.out.println("Substring from index 2 to 6: " + sb2.substring(2, 6));
        System.out.println("Index of 'Pro': " + sb2.indexOf("Pro"));
        // Java Programming
        // 0123456789012345 (length 16)
        System.out.println("Index of 'am' after index 4: " + sb2.indexOf("am", 4));
        System.out.println("Capacity of sb2: " + sb2.capacity()); // capacity is the size of the internal array
        System.out.println("Replacing capacity with 50");
        sb2.ensureCapacity(50);
        System.out.println("Capacity of sb2: " + sb2.capacity());
        // newCapacity = oldCapacity * 2 + 2
        // default capacity is 16
        // so new capacity = 16 * 2 + 2 = 34
        // Forcing to 50 so it chooses max(newCapacity(34), 50) => ((34*2 + 2)=70, 50) => 70

        int numnum = 12345;
        String numnum_str = Integer.toString(numnum);
        System.out.println("numnum_str: " + numnum_str);

        int dub = 10;
        int dub_dub = 3;
        double dub_res = ((double) dub) / dub_dub;
        System.out.println("res: " + dub_res);
        int dub_rem = dub % dub_dub;
        System.out.println("rem: " + dub_rem);

        int xxx = 10;
        if (xxx > 5 & ++xxx > 10) {
            System.out.printf("%d. YES%n", xxx);
        }
        System.out.println(xxx);
        // & is bitwise AND operator
        // && is logical AND operator with short-circuiting
        // (short circuiting means if first condition is false, second condition is not evaluated)
        // but when both sides of & are boolean, it acts as logical AND w/o short-circuiting
        // so both sides are evaluated always
        // we use & and | when we want both sides to be evaluated always (or when we want to do bitwise operations, obviously)

        // assignment chaining
        int p, q, r;
        p = q = r = 50;
        // evaluated right to left
        // 1. r = 50
        // 2. q = r (which is 50)
        // 3. p = q (which is 50)
        System.out.println("p: " + p + ", q: " + q + ", r: " + r);

        // switch
        int day = 3;
        switch (day) {
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            case 4:
                System.out.println("Thursday");
                break;
            case 5:
                System.out.println("Friday");
                break;
            case 6:
                System.out.println("Saturday");
                break;
            case 7:
                System.out.println("Sunday");
                break;
            default:
                System.out.println("Invalid day");
        }

        // for loop
        for (int i = 0; i < 3; i++) {
            System.out.println("i: " + i);
        }
        // while loop
        int j = 0;
        while (j < 3) {
            System.out.println("j: " + j);
            j++;
        }
        // do-while loop
        int k = 0;
        do {
            System.out.println("k: " + k);
            k++;
        } while (k < 3);

        // Modern switch (Java 14+)
        int month = 4;
        switch (month) {
            case 1, 2, 3 -> System.out.println("Winter");
            case 4, 5, 6 -> System.out.println("Spring");
            case 7, 8, 9 -> System.out.println("Summer");
            case 10, 11, 12 -> System.out.println("Autumn");
            default -> System.out.println("Invalid month");
        }

        // LABELS
        outerLoop:
        for (int ii = 0; ii <= 5; ii++) {
            for (int jj = 0; jj <= 5; jj++) {
                if (ii == 2 && jj == 2) {
                    continue outerLoop;
                }
                else if (ii == 4 && jj == 2) {
                    break outerLoop;
                }
                System.out.println("ii: " + ii + ", jj: " + jj);
            }
        }
        // LABELS are used with break and continue statements to specify which loop to break/continue

        // FOR EACH LOOP
        int[] arr = {11, 22, 33, 44, 55};
        for (int num : arr) {
            System.out.println("num: " + num);
        }
        // for each loop is used to iterate over arrays, collections, maps, etc

        // Infinite loop
        int infy_num = 0;
        infyLoop:
        for (;;) {
            System.out.println("infy_num: " + infy_num);
            infy_num++;
            if (infy_num > 5) {
                break infyLoop;
            }
        }

        // METHODS
        // A reusable block of code that executes inside its own stack frame
        // when called, JVM creates a new stack frame for the method
        // parameters are passed via stack frame
        // when method execution is complete, stack frame is destroyed and control returns to caller

        methodA();
        System.out.print("Main ends");
        System.out.println();
        // here, methodA calls methodB. methodB calls methodC.
        // So the stack frames are created as follows:
        // main -> methodA -> methodB -> methodC
        // when methodC completes, its stack frame is destroyed and control returns to methodB
        // when methodB completes, its stack frame is destroyed and control returns to methodA
        // when methodA completes, its stack frame is destroyed and control returns to main

        // Parameter passing:-
        // Java is NOT pass-by-reference
        // Java is pass-by-value ONLY
        // CASE 1:-
        int change_a = 10;
        change(change_a);
        System.out.println("change_a: " + change_a);
        // CASE 2:-
        int change_b = 20;
        int change_c = change(change_b);
        System.out.println("change_b: " + change_b);
        System.out.println("change_c: " + change_c);
        // change_a and change_b remain unchanged (references to 10 & 20 respectively)
        // copy of values that those references are pointing to are passed

        // the update to 100 is destroyed in case 1 as reference remains unchanged and x is destroyed after method ends
        // in case 2, we return the updated value and assign it to change_c


        Box box = new Box();
        box.value = 10;
        System.out.println("Before changeBox, box.value: " + box.value);
        changeBox(box);
        System.out.println("After changeBox, box.value: " + box.value);
        // here, the reference to box is passed by value
        // both box in main and b in changeBox point to the same Box object in heap
        // so when b.value is updated, box.value is updated too (b points to the same object as box in heap)
        // Test it!!
        Box box2 = new Box();
        box2.value = 40;
        System.out.println("Before changeBox, box2.value: " + box2.value);
        Box returnedBox = returnBox(box2);
        System.out.println("returnedBox.value: " + returnedBox.value);
        System.out.println("After returnBox, box2.value: " + box2.value);
        System.out.println("Compare references box2 and returnedBox: " + (box2 == returnedBox));
        // both box2 and returnedBox point to the same Box object in heap

        // METHOD OVERLOADING (OverloadDemo class)
        int overload_a = 10;
        double overload_b = 10.5;
        Integer overload_c = 10;
        OverloadDemo.show(overload_a);
        OverloadDemo.show(overload_b);
        OverloadDemo.show(overload_c);

        // RECURSION
        System.out.println("Recursion:");
        int recurse_start = 5;
        recurse(recurse_start);

        System.out.println("Recursion 2:"); // understand stack frames here too
        recurse2(recurse_start);

        // Method calls inside expressions
        int h; // reading is forbidden by stack frame before initialization
        h = f() + g();
        System.out.println("h: " + h);

        // FINAL (aka FIXED)
        final int final_num = 10;
        // final_num = 20; // cannot be changed
        int final_num2 = final_num + 5; // allowed, as we are not changing the reference, just using its value
        System.out.println("final_num2: " + final_num2);

        final int final_num3;
        final_num3 = 30; // allowed; Compiler does "definite assignment analysis" (can assign ONCE)
        System.out.println("final_num3: " + final_num3);

        // Immutable object example
        User user1 = new User(1);
        System.out.println("User ID: " + user1.id);
        System.out.println("User Name: " + user1.name);
        User user2 = new User();
        user2.name = "Akira";
        System.out.println("User2 ID: " + user2.id);
        System.out.println("User2 Name: " + user2.name);

        StringBuilder str_1 = new StringBuilder("Hello");
        System.out.println("str_1: " + str_1);
        str_1 = new StringBuilder("Hola"); // str_1 points to "Hola" in heap, "Hello" in heap is UNREFERENCED now
        // the original "Hello" string in heap is eligible for garbage collection
        System.out.println("str_1: " + str_1);
        final StringBuilder str_2 = new StringBuilder("Hello");
        str_2.append(" World"); // allowed, as we are not changing the reference
        // just modifying the object in heap it points to
        System.out.println("str_2: " + str_2);
        str_2.replace(0, 5, "Hola"); // allowed
        System.out.println("str_2: " + str_2);
        // str_2 = new StringBuilder("Bonjour"); // NOT allowed, as we are changing the reference
        // final prevents reassignment of the reference, NOT modification of the object it points to

        // final method
        Child child = new Child();
        child.show(); // cannot override final method

        // final class
        Utility util = new Utility();
        util.util();

        // final --> variable (no mutation)
        // final --> reference (no reassignment)
        // final --> method (no overriding)
        // final --> class (no inheritance (subclass))

        // STATIC MEMORY EXAMPLE
        // static memory is allocated only once at the start of program execution (at class loading)
        // shared across all instances of the class (objects)
        Counter c1 = new Counter();
        Counter c2 = new Counter();
        System.out.println("c1.count: " + c1.count);
        System.out.println("c1.sub_count: " + c1.sub_count);
        System.out.println("c2.count: " + c2.count);
        System.out.println("c2.sub_count: " + c2.sub_count);
        c1.incrementCount();
        System.out.println("After c1.incrementCount():");
        System.out.println("c1.count: " + c1.count);
        System.out.println("c1.sub_count: " + c1.sub_count);
        System.out.println("c2.count: " + c2.count);
        System.out.println("c2.sub_count: " + c2.sub_count);
        // static variable 'count' is shared across all instances
        // so when c1 increments count, c2 sees the updated value
        // sub_count is instance variable, so each instance has its own copy
        // so when c1 increments sub_count, c2's sub_count remains unchanged

        // count -> method area (static memory) or metaspace
        // sub_count -> heap (instance memory) (heap is per object, not per class)

        // BLOCK DEMO (STATIC, INSTANCE, CONSTRUCTOR)

        // BlockDemo bd1 = new BlockDemo();
        // no need to store in variable, you can create unreferenced objects
        new BlockDemo();

        // Rules:-
        // 1. Static blocks are executed only once when the class is loaded
        // 2. Instance blocks are executed every time an object is created, before the constructor
        // 3. Constructors are executed every time an object is created, after the instance block

        // STATIC ORDER DEMO
        StaticOrder so1 = new StaticOrder();
        System.out.println("so1.x: " + so1.x); // there is no x inside object, so JVM looks for static variable in class
        // it resolves to StaticOrder.x at compile time
        System.out.println("so1.y: " + so1.y); // instance variables can be accessed via object reference
        System.out.println("StaticOrder.x: " + StaticOrder.x); // static variables can be accessed via class name

        // static and polymorphism
        A aa_obj = new A();
        System.out.println("Reference A, Object A:");
        aa_obj.show(); // A.show() is called
        B bb_obj = new B();
        System.out.println("Reference B, Object B:");
        bb_obj.show(); // B.show() is called
        bb_obj.non_static_show();
        bb_obj.only_b_show();
        A ab_obj = new B();
        System.out.println("Reference A, Object B:");
        ab_obj.show(); // A.show() is called
        ab_obj.non_static_show();
        // ab_obj.only_b_show(); // ERROR: cannot find symbol
        // B ba_obj = new A();
        // ba_obj.show();
        // A cannot be converted to B

        // EXPLANATION:

        // A        aa_obj = new B();
        // ↑                     ↑
        // reference type        actual object created (object type)
        // COMPILE TIME          RUN TIME

        // COMPILE TIME:
        // 0. ab_obj is a variable that can hold references to A-type objects
        // 1. new B() creates an object of type B in heap memory
        // 2. B's constructor is called to initialize the B object
        // 3. A reference (address) to the B object is returned and assigned to ab_obj
        
        // Heap:
        // ┌──────────────┐
        // │   B object   │  ← contains A + B parts
        // └──────────────┘

        // COMPILE TIME:
        // 4. A ab_obj : this happens at compile time
        // 5. A ab_obj : compiler checks that ab_obj can hold references to A-type objects
        // 6. A ab_obj = new B() : compiler checks that B is a subclass of A (is-a relationship) so assignment is valid
        // Compiler decides what methods you can call on ab_obj based on its reference type (A)
        // (basically doesnt give a fuck about what object is created at runtime, after validation of is-a relationship)
        // only cares about methods and fields you access using ab_obj variable of reference type (A)
        // ab_obj.show(); : compiler checks if A has show(), else, ERROR (only cares about reference type, not actual object)

        // Static Methods use reference type, they dont belong to objects (they dont exist in heap memory but in static memory)
        // Static methods are resolved at compile time (hence using reference type)
        // Instance methods are resolved at runtime (hence using object type)
        // ab_obj.show(); : uses show() method of A because reference type is A

        // ab_obj.non_static_show()
        // Non-static methods are POLYMORPHIC
        // They're resolved at RUNTIME (hence using object type)
        // 7. ab_obj.non_static_show() : compiler checks if A has non_static_show(), else, ERROR
        // if yes, ALLOWED TO CALL (thats all compiler is supposed to do)

        // RUNTIME:
        // 8. ab_obj.non_static_show()
        // JVM looks at actual object -> new B();
        // JVM calls B's implementation of non_static_show() [ method overriding ]
        // B.non_static_show() is called

        // TLDR; reference type is used at compile time, object type is used at runtime
        // TLDR; compile time -> checks if allowed (sanity)
        // TLDR; runtime -> accesses implementations (of methods and fields)

        // Static classes cannot access instance variables
        System.out.println("Static Class Demo: ");
        Outer.Inner obj = new Outer.Inner();
        obj.show();

        // static --> variable (exists in static memory (all objects use same static memory for static fields))
        // static --> method (uses reference type at compile time AKA NON-polymorphic)
        //            can only use static memory variables
        // static --> block (only executed once at class loading)
        // static --> nested-class (cannot access instance fields of outer class)

        // random info dump
        // final does NOT mean immutable
        // final means CANNOT be reassigned
        
        // WRAPPER CLASSES
        // int -> Integer
        // double -> Double
        // boolean -> Boolean
        // char -> Character
        // long -> Long
        // float -> Float
        // short -> Short
        // byte -> Byte

        Integer Integer_a = 10;       // 1. autoboxing (int → Integer)
        int int_a = Integer_a;        // 2. unboxing (Integer → int)
        System.out.println("Integer_a: " + Integer_a);
        System.out.println("int_a: " + int_a);
        // 1. Integer a = Integer.valueOf(10); in background; Integer object in Integer cache heap
        // 2. int b = a.intValue(); in background; primitive value stored directly in stack frame
        System.out.println("Integer_a == int_a: " + (Integer_a == int_a)); 
        // TRUE; in bg, it is "Integer_a.intValue() == int_a"
        // Integer_a is unboxed so it becomes 10 == 10, value comparision instead of reference comparision


        // ARRAYS
        System.out.println("ARRAYS");
        int[] arr_a = {1, 2, 3}; // option 1: array literal (short-hand initialization)
        int[] arr_b = new int[]{1, 2, 3}; // option 2: new keyword
        int[] arr_c;
        // arr_c = {1, 2, 3}; // cannot use shorthand like this
        arr_c = new int[]{1, 2, 3}; // only option 2 

        // array is an object, hence, stored in heap

        // STACK: (holds reference variables)
        // arr ───────▶ HEAP
        // HEAP:
        // int[5] -> [0, 0, 0, 0, 0]
        System.out.println("arr_a: " + Arrays.toString(arr_a));
        arr_a[0] = 10;
        System.out.println("arr_a: " + Arrays.toString(arr_a));
        doubleTheArray(arr_a);
        // passes COPY OF REFERENCE to method
        System.out.println("After doubleTheArray(arr_a): " + Arrays.toString(arr_a));
        int[] reset_arr = reset(arr_a);
        System.out.println("After reset(arr_a): " + Arrays.toString(arr_a));
        System.out.println("reset_arr: " + Arrays.toString(reset_arr));

        // String Array
        String[] arr_strings = new String[]{"a", "b", "c"};
        System.out.println("arr_strings: " + Arrays.toString(arr_strings));
        arr_strings[0] = "x";
        System.out.println("arr_strings: " + Arrays.toString(arr_strings));
        double double105 = 10.5;
        arr_strings[1] = String.valueOf(double105);
        System.out.println("arr_strings: " + Arrays.toString(arr_strings));

        // Array dimension
        // when we initialize array, we can either use "new int[5]" or "new int[]{1, 2, 3, 4, 5}"
        String[] names = new String[3];
        names[0] = "Name A";
        names[1] = "Name B";
        names[2] = "Name C";
        System.out.println("names: " + Arrays.toString(names));
        System.out.println("size of names array: " + names.length);
        System.out.println("size of names array in double: " + (double) names.length);
        String[] names2 = new String[]{"Name A", "Name B", "Name C"};
        System.out.println("names2: " + Arrays.toString(names2));
        System.out.println("names == names2: " + (names == names2)); // FALSE; different objects in heap
        System.out.println("Arrays.equals(names, names2): " + Arrays.equals(names, names2)); // TRUE
        // shuffle of names2
        String[] names3 = new String[]{"Name C", "Name A", "Name B"};
        System.out.println("names3: " + Arrays.toString(names3));
        System.out.println("---> Arrays.equals(names, names3): " + Arrays.equals(names, names3)); // FALSE
        // Arrays.equals(x, y) x and y must have same order

        // Sorting
        System.out.println("Arrays.sort();");
        System.out.println("names: " + Arrays.toString(names));
        System.out.println("names3: " + Arrays.toString(names3));
        Arrays.sort(names);
        Arrays.sort(names3);
        System.out.println("Arrays.sort(names): " + Arrays.toString(names));
        System.out.println("Arrays.sort(names3): " + Arrays.toString(names3));
        System.out.println("---> Arrays.equals(names, names3): " + Arrays.equals(names, names3)); // TRUE

        // PRIVATE ACCESS MODIFIER
        PrivateDemo pd = new PrivateDemo();
        // pd.privateShow(); // cannot access private method outside class
        pd.defaultShow();

        // PROTECTED ACCESS MODIFIER
        ProtectedDemo pd2 = new ProtectedDemo();
        pd2.protectedShow();

        // THIS/SUPER KEYWORD
        ThisDemo td = new ThisDemo();
        int this_x = 10;
        td.incrementWithoutThis(this_x);
        td.incrementWithThis(this_x);
        System.out.println("td.this_x: " + td.this_x);

        Child child2 = new Child();
        child2.thisShow();
        child2.superShow();

        // INSTANCEOF
        StringBuilder str_obj = new StringBuilder("ABC123");
        if (str_obj instanceof StringBuilder) {
            StringBuilder str_obj2 = (StringBuilder) str_obj;
            System.out.println("str_obj == str_obj2: " + (str_obj == str_obj2)); // same reference
            str_obj2.append("DEF456");
        }
        System.out.println("str_obj: " + str_obj);
        // in JAVA 16+
        if (str_obj instanceof StringBuilder str_obj3) {
            System.out.println("str_obj == str_obj3: " + (str_obj == str_obj3));
            str_obj3.append("GHI789");
        }
        System.out.println("str_obj: " + str_obj);

        // INTERFACES
        // Interface is a CONTRACT that states:- a class must implement ALL methods of the interface
        // Interface Memory Model:- 
        // Interfaces do not form objects. They do NOT live in heap
        // They exist in method area (static memory) or metaspace (they share home with static members)
        Flyable birb = new Bird(); // not a typo :)
        // When "Flyable birb = new Bird();" is executed:-

        // STACK:
        // f  ───────────▶ HEAP

        // HEAP:
        // Bird object
        // ├── Object header
        // ├── Bird fields
        // ├── Parent fields
        // └── method table (implements Flyable)

        // METHOD AREA:
        // interface Flyable

        birb.fly();
        // COMPILE TIME:-
        // compiler checks does Flyable interface have fly() method
        // RUN TIME:-
        // runtime looks at the object and calls Bird.fly() method

        AnimalInterface a1 = new Dog();
        AnimalInterface a2 = new Cat();

        a1.makeSound(); // Dog barking
        a2.makeSound(); // Cat meowing
        // COMPILE TIME:-
        // compiler checks does AnimalInterface interface have makeSound() method and if its allowed
        // RUN TIME:-
        // runtime looks at the object and calls Dog.makeSound() method

        playSound(a1); // Dog barking
        playSound(a2); // Cat meowing

        // class Dog extends Animal: this would show IDENTITY (heirchary of belongance)
        // class Dog implements Animal: this would show CAPABILITY (what dog can do (everything provided in Animal contract))
        // in extension, we can only extend to 1 parent class
        // in implementation, we can implement multiple interfaces

        RobotDog rd = new RobotDog();
        rd.eat();
        rd.recharge();
        rd.bark();


        // GENERICS:-
        // (foundation for Collections (based on Interfaces))
        // When you consider an array of 'objects', compiler cannot know what type of object it is

        // Object[] objArray = new String[5]; // ---(1)
        // objArray[0] = 3.14; // ---(2)
        // (1) ---> arrays in JAVA are covariant (String is subtype of Object so String[] is subtype of Object[])
        // (2) ---> compiler reads reference type (Object[]) and double is an object so type checking for 3.14 passes at compile time
        // (you can uncomment and see run javac 'hello.java' to see it works but 'java hello' fails)

        // generic is used to do type-checking at runtime to prevent type-checking issues with Object arrays
        // in better words: help compiler type-check through reference type using 'generic types'
        List<String> list_ = new ArrayList<>();
        // <String> is 'generic type' that creates a compile-time constraint on List object
        // connectiong to interfaces:-
        // List is an interface
        // ArrayList is a class that implements List interface (list_ is a resizable object of type ArrayList)
        // List interface creates a contract over methods add(), remove(), get(), size(), etc.

        // List<String> list_ = new LinkedList<>(); // we can simply switch the object type since it also implements List interface
        // this, that statement has 'loose coupling'
        // ArrayList<String> list_ = new ArrayList<>(); // this statement has 'tight coupling' 

        List<String> generic_list_a = new ArrayList<>();
        List<Integer> generic_list_b = new ArrayList<>();
        System.out.println("generic_list_a.getClass(): " + generic_list_a.getClass()); // "class java.util.ArrayList"
        System.out.println("generic_list_b.getClass(): " + generic_list_b.getClass()); // "class java.util.ArrayList"
        // JAVA is type erasure (JVM removes type information at runtime)
        // Generic types do NOT exist at runtime

        // GENERIC METHODS:-
        genericPrint("Generic Hello");
        genericPrint(314);
        genericPrint(3.14);
        // Compiler infers <T> 


        // WILDCARDS
        List<Integer> ints = new ArrayList<>(); // ---(1)
        List<Integer> nums = ints; // ---(2)
        // (1) ---> ArrayList object is created in heap. ints is reference
        // (2) ---> nums points to same ArrayList object in heap

        // GENERICS; UPPER-BOUND:-
        List<Integer> ubs1 = new ArrayList<>();
        ubs1.add(1);
        ubs1.add(2);
        ubs1.add(3);
        upperBoundSum(ubs1);
        List<Double> ubs2 = new ArrayList<>();
        ubs2.add(1.0);
        ubs2.add(2.0);
        ubs2.add(3.0);
        upperBoundSum(ubs2);
        // List<Object> ubs3 = new ArrayList<>();
        // ubs3.add(1);
        // ubs3.add(2);
        // ubs3.add(3);
        // upperBoundSum(ubs3); // upper-bound function with Number (<? extends Number>) can only accept Number and its subclasses
        // Object is superclass

        // GENERICS; LOWER-BOUND:-
        List<Number> lbs1 = new ArrayList<>();
        lbs1.add(1);
        lbs1.add(2);
        lbs1.add(3);
        lowerBoundSum(lbs1);
        List<Object> lbs2 = new ArrayList<>();
        lbs2.add(1);
        lbs2.add(2);
        lbs2.add(3);
        lowerBoundSum(lbs2);
        // List<Integer> lbs3 = new ArrayList<>();
        // lbs3.add(1);
        // lbs3.add(2);
        // lbs3.add(3);
        // lowerBoundSum(lbs3); // lower-bound function with Number (<? super Number>) can only accept Number and its superclasses
        // Integer is subclass


        // COLLECTIONS:-
        // The Collections Framework is a set of interfaces + implementations
        // Iterable -> Collection -> List -> ArrayList/LinkedList/Vector
        //                             >>> List is an interface
        //                             >>> List<String> list = new ArrayList<>(); List is interface and ArrayList is implementation

        // double LinkedList (3 references; lives in heap)
        // class Node<E> {
        //     E item;
        //     Node<E> next;
        //     Node<E> prev;
        // }
        // [prev] ← Node(A) → [next] ← Node(B) → [next] ← Node(C)
        List<String> list = new LinkedList<>();
        list.add("Aa");
        list.add("Bb");
        list.add("Cc");
        System.out.println(list);
        list.add(1, "AaBb");
        System.out.println(list);
        list.remove(1);
        System.out.println(list);
        System.out.println(list.get(1));
        // all look-ups are O(n)

        // DEQUEUE (double ended queue)
        System.out.println("DEQUEUE");
        Deque<String> dq = new LinkedList<>();
        dq.addFirst("A");
        dq.addLast("B");
        dq.addLast("C");
        System.out.println(dq);
        dq.removeFirst();
        System.out.println(dq);
        dq.removeLast();
        System.out.println(dq);
        // JAVA 21 onwards, List extends SequencedCollection which provide addFirst, addLast, etc.
        List<String> dq2 = new LinkedList<>();
        dq2.addFirst("D");
        dq2.addLast("E");
        dq2.addLast("F");
        System.out.println(dq2);
        dq2.removeFirst();
        System.out.println(dq2);
        dq2.removeLast();
        System.out.println(dq2);
        // Deque provides O(1) addFirst, addLast, removeFirst, removeLast
        // List does not guarantee O(1) addFirst, addLast, removeFirst, removeLast (depends on how you implement it)

        // Deque is the correct abstraction when you want queue / stack
        // It defines behavior *and* performance expectations (O(1) add/remove at both ends)
        // LinkedList is one possible implementation of Deque (ArrayDeque is often better)
        Deque<String> dq3 = new ArrayDeque<>(); // cannot use List reference type
        dq3.addFirst("G");
        dq3.addLast("H");
        dq3.addLast("I");
        System.out.println(dq3);
        dq3.removeFirst();
        System.out.println(dq3);
        dq3.removeLast();
        System.out.println(dq3);
        // List represents a general ordered sequence with positional access.=
        // Since Java 21, List extends SequencedCollection and exposes first/last operations
        // but these are convenience methods, not performance guarantees
        // Use List when order/indexing matters; use Deque when end-based operations matter

        
        // ENCAPSULATION
        // Encapsulation refers to an object controlling it's own internal state and how it's accessed/read
        // It is not simply 'hide internals'
        // 'Invalid state' of an object shouldn't exist

        System.out.println("ENCAPSULATION");
        // Integer marks
        StudentRecord student1 = new StudentRecord("A");
        student1.setMarks(100); // setter
        System.out.println(student1.getName()); // getter
        System.out.println("Marks: " + student1.getResult().marks());
        System.out.println("Grade: " + student1.getResult().grade());
        System.out.println("student1.getResult() :" + student1.getResult()); // student1.getResult() :Result[marks=100.0, grade=S]
        // Result is a 'Nested Type'
        StudentRecord.Result student1_result = student1.getResult();
        System.out.println("student1_result.marks() :" + student1_result.marks());
        System.out.println("student1_result.grade() :" + student1_result.grade());

        // Invalid marks (ignored safely)
        StudentRecord student2 = new StudentRecord("B");
        student2.setMarks(101);   // rejected
        System.out.println(student2.getName());
        System.out.println("Marks: " + student2.getResult().marks());
        System.out.println("Grade: " + student2.getResult().grade());
        System.out.println("student2.getResult() :" + student2.getResult());

        // Decimal marks
        StudentRecord student3 = new StudentRecord("C");
        student3.setMarks(65.5);
        System.out.println(student3.getName());
        System.out.println("Marks: " + student3.getResult().marks());
        System.out.println("Grade: " + student3.getResult().grade());
        System.out.println("student3.getResult() :" + student3.getResult());

        // Slightly invalid decimal
        StudentRecord student4 = new StudentRecord("D");
        student4.setMarks(100.01); // rejected
        System.out.println(student4.getName());
        System.out.println("Marks: " + student4.getResult().marks());
        System.out.println("Grade: " + student4.getResult().grade());
        System.out.println("student4.getResult() :" + student4.getResult());
    }
}
