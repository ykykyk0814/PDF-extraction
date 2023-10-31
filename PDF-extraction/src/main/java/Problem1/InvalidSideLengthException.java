package Problem1;
//reference:how to create a new exceptionfield:https://stackoverflow.com/questions/22553688/how-to-write-custom-exception-with-user-defined-fields
public class InvalidSideLengthException extends Exception{
        public InvalidSideLengthException(String message) {
            super(message);
        }
    }
