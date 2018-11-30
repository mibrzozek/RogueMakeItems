import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main
{
    private static String syntax, refSyntax, file;

    public static void main(String[] args)
    {
        initSyntaxStrings();

        try ( Scanner fileScan = new Scanner( new File("itemsList.txt")))
        {
            int count = 0;
            String newItemMethod = "";
            // skip the first line

            while(fileScan.hasNextLine())
            {
                String   oneLine = fileScan.nextLine();

                if(oneLine.startsWith("//")) // Skips comments in file
                    continue;

                if(count == 0)
                {
                    // Loads up method with variable
                    // Replaces all variable with String
                    newItemMethod = syntax;
                    newItemMethod = newItemMethod.replaceAll("@", oneLine);
                    // Changes the method header to lowerCamelCase
                    char[] charray = oneLine.toCharArray();
                    Character lowerC = charray[0];
                    String upperC = lowerC.toString().toUpperCase();
                    String capitalMethod = oneLine.replaceFirst(lowerC.toString() , upperC);

                    newItemMethod = newItemMethod.replaceFirst(oneLine, capitalMethod);
                }
                else if(count == 1)
                {
                    newItemMethod = newItemMethod.replaceAll("#", oneLine);
                }
                else if(count == 2)
                {
                    newItemMethod = newItemMethod.replaceAll("\\$", oneLine);
                }
                else if(count == 3)
                {
                    newItemMethod = newItemMethod.replaceAll("%", oneLine);
                }
                else if(count == 4)
                {
                    newItemMethod = newItemMethod.replaceAll("\\^", oneLine);
                }
                else if(count == 5)
                {
                    newItemMethod = newItemMethod.replaceAll("&", oneLine);
                }
                else if(count == 6)
                {
                    newItemMethod = newItemMethod.replaceAll("\\*", oneLine);
                }
                else if(count == 7)
                {
                    newItemMethod = newItemMethod.replaceAll("~", oneLine);
                }
                else if(count == 8)
                {
                    newItemMethod = newItemMethod.replaceAll("\\+", oneLine);

                    file += newItemMethod;
                    newItemMethod = syntax;
                }
                count++;
                if(count == 9)
                    count = 0;
            }
        }
        catch(NullPointerException e) {
            System.err.println("File Was Not Selected");
        }
        catch(FileNotFoundException e) {
            System.err.println("File Not Found");
        } catch(NoSuchElementException e) {
            System.err.println("No More Token Are Available");
        }

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("ItemFactory.java"), "utf-8")))
        {
            file += "}";
            writer.write(file);
            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void initSyntaxStrings()
    {
        /**
         *
         */
        file =
                "package items;\n" +
                        "\n" +
                        "import java.awt.Color;\n" +
                        "import java.io.Serializable;\n" +
                        "import java.util.Random;\n" +
                        "\n" +
                        "import asciiPanel.AsciiPanel;\n" +
                        "import items.Item.Type;\n" +
                        "import wolrdbuilding.Palette;\n" +
                        "import wolrdbuilding.World;\n" +
                        "\n" +
                        "public class ItemFactory implements Serializable\n" +
                        "{\n" +
                        "\tprivate World world;\n" +
                        "\tpublic Random r;\n" +
                        "\t\n" +
                        "\tpublic ItemFactory(World world)\n" +
                        "\t{\n" +
                        "\t\tthis.world = world;\n" +
                        "\t\tthis.r = new Random();\n" +
                        "\t}\n" +
                        "\tpublic ItemFactory()\n" +
                        "\t{\n" +
                        "\t\tthis.world = null;\n" +
                        "\t}\n\n";
        refSyntax =
                "\tpublic Item newInvisibilityCloak()\n" +
                        "\t{\n" +
                        "\t\tItem cloak = new Item((char)131, AsciiPanel.brightBlue, Type.STEALTH ,\n" +
                        "\t\t\t\t\"Loin Cloak\", \n" +
                        "\t\t\t\t\"It's better than being seen!\",\n" +
                        "\t\t\t\t0, 200\n" +
                        "\t\t\t\t, 50);\n" +
                        "\t\tif(world != null)\n" +
                        "\t\t\tworld.spawnInside(r.nextInt(5), cloak);\n" +
                        "\t\treturn cloak;\n" +
                        "\t}";
        syntax =
                "\tpublic Item new@()\n" +
                        "\t{\n" +
                        "\t\tItem @ = new Item((char)#, AsciiPanel.$, Type.% ,\n" +
                        "\t\t\t\t\"^\", \n" +
                        "\t\t\t\t\"&\",\n" +
                        "\t\t\t\t*, ~\n" +
                        "\t\t\t\t, +);\n" +
                        "\t\tif(world != null)\n" +
                        "\t\t\tworld.spawnInside(r.nextInt(5), @);\n" +
                        "\t\treturn @;\n" +
                        "\t}\n";
    }
}
