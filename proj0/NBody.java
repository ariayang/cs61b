public class NBody {

    /* Given a file name as a String. 
    * @return: a double, radius of the universe, which is the 2nd Integer in the file
    */
    public static double readRadius(String filename) {
        In in = new In(filename);
        int NumberofPlanet = in.readInt();
        double radius = in.readDouble();
        in.close();
        return radius;
    }

    /* Given a file name as a String. 
    * @return: an array of Bodys, which starts from 3rd line in the file
    */
    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int NumberofPlanets = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[NumberofPlanets];
        for (int i = 0; i < NumberofPlanets; i++) {
            planets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
        }
        in.close();
        return planets;
    }

    public static void main(String args[]) {
        if (args.length == 0) {
			System.out.println("Please supply 2 doubles, and a filename as a command line argument.");
			System.out.println("Example: java NBody 157788000.0 25000.0 data/planets.txt");
		}	
        /* Start reading in the arguments */

        //Store 0th and 1st command line arguments as doubles, T, dt
        //Argument comes in as Strings, convert String to Double: double dnum = Double.parseDouble(String)
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);

        //Store 2nd command line arguments as String: filename
        String filename = args[2];

        //Read in the bodies and the universe radius
        Planet[] bodiesArray = readPlanets(filename);
        double radius = readRadius(filename);
       

        /* Now drawing the background images/starfield.jpg, use StdDraw library. */
        String imageToDraw = "images/starfield.jpg";
        /* set the scale so that it matches the radius of the universe. 
        * Then draw the image starfield.jpg as the background. */
        /** Enables double buffering.
		  * A animation technique where all drawing takes place on the offscreen canvas.
		  * Only when you call show() does your drawing get copied from the
		  * offscreen canvas to the onscreen canvas, where it is displayed
		  * in the standard drawing window. */
		StdDraw.enableDoubleBuffering();

		/** Sets up the universe so it goes from
		  * -radius, -radius up to radius, radius */
		StdDraw.setScale(-radius, radius);

		/* Clears the drawing window. */
		StdDraw.clear();

        /* Draw background. */
		StdDraw.picture(0, 0, imageToDraw);



        for (Planet mybody: bodiesArray) {
            mybody.draw();
        }
        /* Shows the drawing to the screen, and waits 2000 milliseconds. */
		StdDraw.show();
		StdDraw.pause(2000);

        /* To draw the animation */
       
        // Loop time from 0 to T at dt
        for (double timeVar = 0; timeVar <=T; timeVar = timeVar+dt) {
            double[] xForces = new double [bodiesArray.length];
            double[] yForces = new double [bodiesArray.length];
            int i = 0;
            for (Planet mybody: bodiesArray) {
                xForces[i] = mybody.calcNetForceExertedByX(bodiesArray);
                yForces[i] = mybody.calcNetForceExertedByY(bodiesArray);
                i++;
            }
            i = 0;
            for (Planet mybody: bodiesArray) {
                mybody.update(dt, xForces[i], yForces[i]);
                i++;
            }
            StdDraw.picture(0, 0, imageToDraw);
            for (Planet mybody: bodiesArray) {
                mybody.draw();
            }
            StdDraw.show();
		    StdDraw.pause(20);
        }

        /* Print out the final state */
        StdOut.printf("%d\n", bodiesArray.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodiesArray.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  bodiesArray[i].xxPos, bodiesArray[i].yyPos, bodiesArray[i].xxVel,
                  bodiesArray[i].yyVel, bodiesArray[i].mass, bodiesArray[i].imgFileName);   
        }
	}
}   
