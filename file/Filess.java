package file;
import java.io.*;
import java.util.Scanner;
 public class Filess  {
public native boolean createMethod(String text,String fl);
public native boolean stringMethod(String text,String fl);
public native boolean deleteMethod(String text);
public Filess()
{
System.loadLibrary("Filess");
}
public boolean create(String s,String n)
{
Filess f = new Filess();
boolean b = f.createMethod(s,n);
return b;
}
public boolean modify(String s,String n)
{
Filess f = new Filess();
boolean b = f.stringMethod(s,n);
return b;
}
public boolean deletes(String s)
{
Filess f = new Filess();
boolean b = f.deleteMethod(s);
return b;
}
public static void main(String args[])throws IOException
{String filn;

Filess sample = new Filess();
int ch;
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
Scanner s= new Scanner(System.in);
System.out.println("1.Create File");
System.out.println("2.Rename File");
System.out.println("3.Delete File");
System.out.println("Enter your choice");
ch=s.nextInt();
switch(ch)
    {
case 1:
System.out.println("Enter the filename with txt extension");
filn=br.readLine();
System.out.println("Enter contents of file :");
String con;
con=br.readLine();
     boolean bool   = sample.create(filn,con);
if (bool)
{

System.out.println("File is created!");
} else {
    System.out.println("File not created or already exists.");
}

break;
case 2:
System.out.println("Enter the old filename with txt extension");
filn=br.readLine();
System.out.println("Enter the new filename with txt extension");
String filn1;
filn1=br.readLine();
     boolean text   = sample.modify(filn,filn1);
if(text)
System.out.println("File renamed  successfully.");
else
System.out.println("File renamed was unsuccessful.");
break;
case 3:     
System.out.println("Enter the filename with txt extension");
String f;
f=br.readLine();
boolean b      = sample.deletes(f);
  if(b)
System.out.println("File is Deleted");
else
System.out.println("File not found or not deleted");
break;
default:
System.exit(0);
    
     
     
   }
   }
}
   
