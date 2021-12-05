/* Garcia, Christine Joy
    Milla, Rogelio Adrian */


import java.io.*;
    
	public class lexer {
		/*character classifications*/
	       private static final int LETTER=0;
	       private static final int DIGIT=1;
	       private static final int UNKNOWN=2;
	       private static final int EOF =-1;
	      
	       private static int size;       /*size of lex*/
	       private static int lexClass;
	       private static char lexArr[];
	       private static char lexeme;
	       private static String token;
	       private static File file;
	       private static FileInputStream fis;
	       
	       
	       public static void addChar()      /*adds the character to an array with size of 10*/
	       {
	            if (size <= 8)
	            {
	                lexArr[size++] = lexeme;
	                lexArr[size] = 0;
	            }
	            else
	               System.out.println("Lexeme is too long");
	       }
	       
	       
	       public static void getChar()      /* gets a character from the file & categorize it into letter, digit, unknown*/
	       {
	           try
	           {
	        	   if(fis.available()>0)
	        	   {
	        		 lexeme = (char) fis.read();
	        		 
	        		 if(Character.isLetter(lexeme))
	            	   lexClass = 0;
	        		 else if(Character.isDigit(lexeme))
	            	   lexClass = 1;
	        		 else
	            	   lexClass = 2;
	        	   }  else
	            	   lexClass = -1;
	           }
	           catch(IOException e)
	           {
	               e.printStackTrace();
	           }
	       }
	       
	       
	       public static String lookup(char ch)    /*function that returns a token if lexClass = Unknown*/
	       {
	    	  switch (ch)
	           {
	           case '=':
	               addChar();
	               token = "ASSIGN_OP";
	               break;
	            case '(':
	                addChar();
	                token = "L_PAREN";
	                break;
	            case ')':
	                addChar();
	                token = "R_PAREN";
	                break;
	            case '+':
	                addChar();
	                token = "PLUS_OP";
	                break;
	            case '-':
	                addChar();
	                token = "SUB_OP";
	                break;
	            case '*':
	                addChar();
	                token = "MULT_OP";
	                break;
	            case '/':
	                addChar();
	                token = "DIV_OP";
	                break;
	            default:
	                addChar();
	                token = "EOF";   /*if a token of an unknown symbol is not classified above , token is EOF*/
	                break;
	           }
	           return token;
	       }
	      
	       
	       public static void notSpace()    /*function that checks for whitespaces in the input file*/
	       {
	           while(Character.isWhitespace(lexeme))
	               getChar();
	       }
	       
	       
	       public static void lex()
	       {
	    	   size = 0;
	    	   notSpace();
	    	   switch (lexClass)
	            {
	               case LETTER:    /*lex continuously calls getChar and addChar*/
	                    addChar();
	                    getChar();
	                    while (lexClass == LETTER || lexClass == DIGIT)
	                    {
	                       addChar();
	                       getChar();
	                   }
	                    token= "IDENTIFIER ";    /*IDENTIFIER for variable names/identifiers and */
	                    break;
	                case DIGIT:   /* go to getChar and addChar*/
	                    addChar();
	                    getChar();
	                    while(lexClass == DIGIT)
	                    {
	                        addChar();
	                        getChar();
	                    }
	                    token = "INT_LITERAL ";   /*INT_LITERAL for integer literals*/
	                    break;
	                case UNKNOWN:
	                    lookup(lexeme);      /*go to lookup function for the symbol’s token*/
	                    getChar();
	                    break;
	                case EOF:
	                    token = "EOF";
	                    break;
	            } 
	       }
	       
	       public static void printLex(String token)    /*function that prints the lexeme and lexeme's token*/
	       {
	    	   System.out.print("Lexeme: ");
	    	   for(int i = 0; i < size; i++)
	               System.out.print(lexArr[i]);
	               System.out.print("\t Token: "+ token + "\n");
	       }
	       
	       
	       public static void main(String args[])
	       {
	    	   size = 0;
	           lexArr = new char[10];
	           
	           for(int i=0; i<10; i++)
	               lexArr[i]='0';
	           
	           file = new File("C:\\Users\\FLEX5\\eclipse-workspace\\CMSC 124\\src\\INPUT.txt");    /*INPUT.TXT FILE*/
	          
	           if (!file.exists())
	           {
	                 System.out.println("FILE CANNOT BE OPENED");
	                 return;
	           }
	           
	           try                                           /*checking for file error*/
	           {
	                 fis = new FileInputStream(file);   
	  
	                 while (fis.available() > 0)      
	                 {  
	                     lex();
	                     printLex(token);
	               }
	           }
	           
	           catch (IOException e)
	           {
	                 e.printStackTrace();
	           }
	       }
	}
