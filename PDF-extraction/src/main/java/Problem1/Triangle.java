package Problem1;


class Triangle {
    public static void checkifvalid(double a, double b, double c) throws InvalidSideLengthException{
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new InvalidSideLengthException("invalid sidelength; all smaller than 0");
        }
        if (a + b<= c || b + c<= a || a + c<= b){
            throw new InvalidSideLengthException("invalid sidelength;unbalanced sides");
        }
    }
}



