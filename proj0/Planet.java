class Planet {
    public double xxPos;   //current x position
    public double yyPos;   //current y position
    public double xxVel;   //current velocity in x direction
    public double yyVel;   //current velocity in y direction
    public double mass;    //its mass
    public String imgFileName;     //Name of the file corresponds to the body image, for example: jupiter.gif

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    };

    /* Calculate the distance between two Body: this and Input Body b
    @return: a double, distance between the 2 bodies. 
    */
    public double calcDistance(Planet b) {
        double dis = java.lang.Math.sqrt((xxPos - b.xxPos)*(xxPos - b.xxPos) + (yyPos - b.yyPos)
        *(yyPos - b.yyPos));
        return dis;
    }

    /* Calculate force exerted on this body by body b
    @return: a double, the exerted force 
    Hint: Should be calling calcDistance
    Hint: Java supports scientific notation, such as double num = 1.03e-7;
    */
    public double calcForceExertedBy (Planet b) {

        double G = 6.67e-11;
        double force = G * mass * b.mass / (calcDistance(b)*calcDistance(b));
        return force;
    }

    /* Calculate force exerted on this body by body b on x direction
    @return: a double, the exerted force on x direction
    */
    public double calcForceExertedByX (Planet b) {
        double deltaX = b.xxPos - xxPos;
        System.out.println(deltaX);
        return calcForceExertedBy(b) * deltaX / calcDistance(b);
    }

    /* Calculate force exerted on this body by body b on y direction
    @return: a double, the exerted force on y direction
    */
     public double calcForceExertedByY (Planet b) {
        double deltaY = b.yyPos - yyPos;
        return calcForceExertedBy(b) * deltaY / calcDistance(b);
    }

    /* Calculate net force exerted on this body by bodies in an body array in x direction
    @return: a double, the exerted net force
    */
    public double calcNetForceExertedByX (Planet[] bArray) {
        double netForceX = 0;
        for (int i = 0; i <=bArray.length-1; i++) {
            if (this.equals(bArray[i])) {continue;}
            netForceX = netForceX + calcForceExertedByX(bArray[i]);
        }
        return netForceX;
    }

    /* Calculate net force exerted on this body by bodies in an body array in y direction
    @return: a double, the exerted net force
    */
    public double calcNetForceExertedByY (Planet[] bArray) {
        double netForceY = 0;
        for (int i = 0; i <=bArray.length-1; i++) {
            if (this.equals(bArray[i])) {continue;}
            netForceY = netForceY + calcForceExertedByY(bArray[i]);
        }
        return netForceY;
    }


    /* To update the body's velocity, and position 
    * in a small period of time, dt, with x-force fX, with y-force fY
    */
    public void update(double dt, double fX, double fY) {
        //Calculate accelaration with fX, fY
        double netAcceX = fX / mass;
        double netAcceY = fY / mass;

        //Calculate new velocity using acceleration and current velocity
        // new velocity: vx+dt*ax, vy+dt*ay
        xxVel = xxVel + dt * netAcceX;
        yyVel = yyVel + dt * netAcceY;

        //Calculate new position with new velocity
        //px+dt*vx, py+dt*vy
        xxPos = xxPos + xxVel * dt;
        yyPos = yyPos + yyVel * dt;
    }


    /* To draw a Body's image at the Body's position. 
    * Takes no parameters, and return nothing */
    public void draw () {
        String imagetoDraw = "images/" + imgFileName;
        System.out.println(imagetoDraw);
        StdDraw.picture(xxPos, yyPos, imagetoDraw);
    }
}   