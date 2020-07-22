package jmm;

public class Parent {
    static {
        A = 200;
    }
    public static int A = 100;
    static {
        System.out.println("parent静态代码块：A="+A);
    }

    public int c = 100;
    public String name;
    {
        System.out.println("parent实例代码块："+c);
    }
    public Parent(int c, String name){
        this.c = c;
        this.name = name;
        System.out.println("parent构造方法");
        print();
    }
    public void print(){
        System.out.println("Parent print, c="+c+", name=Parent");
    }
    public static void main(String[] args) {
        new Child(200);
    }
}
class Child extends Parent {
    static {
        A = 400;
    }
    public static int A = 300;
    {
        System.out.println("child实例代码块："+c);
    }
    static {
        System.out.println("child静态代码块：A="+A);
    }
    public Child(int c) {
        super(c, "Child");
        System.out.println("child构造方法");
    }
    @Override
    public void print() {
        System.out.println("Child print, c="+c+", name="+name);
    }
}
