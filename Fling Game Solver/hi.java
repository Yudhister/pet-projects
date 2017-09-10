class my{
    ur instance;
    my() {
        instance = new ur();
    }
    ur getInstance () {
        return instance;
    }
    int a=1;
    public class ur{
        ur () {
        }
        int a=2;
    }
}

public class hi{
    public static void main(){
        System.out.println((new my()).a);
        //System.out.println((new my$ur()).a);
    }
}