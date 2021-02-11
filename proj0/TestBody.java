class TestBody {
    public static void main (String[] args) {
        /* Body(double xP, double yP, double xV, double yV, double m, String img) 
        */
        //Body a1 = new Body(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        //Body a2 = new Body(1.0, 7.0, 5.0, 4.0, 1e+7, "jupiter.gif");
        //System.out.println ("Testing pairwise force between Bodies a1, a2");
        //checkEquals(a1.calcForceExertedBy(a2), 2.8732, "calcForceExertedBy()", 0.01);


        Body b1 = new Body(1.0, 0.0, -999, -999, 7e5, "samh.gif");
        Body b2 = new Body(3.0, 3.0, -999, -999, 8e5, "aegir.gif");
        Body b3 = new Body(5.0, -3.0, -999, -999, 9e6, "rocinante.gif");

        checkEquals(b1.calcForceExertedBy(b2), 2.8732, "calcForceExertedBy()", 0.01);
    }
}