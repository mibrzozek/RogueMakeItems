import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class RogueMakeItems
{
    /**
     * Purpose  : Adds factory methods to the ItemFactory class
     *            based on the itemsList.txt found in this program.
     *            You update the file, and the program produces a
     *            ItemFactory with the appropriate factory methods
     * Author   : Michal Brzozek
     *
     */
    private static String syntax, refSyntax, file;
    private static final String ROGUE_PATH = "C:\\006 SOURCE\\01 JAVA PROJECTS\\004 ROGUE ONE\\RogueOne\\src\\items\\ItemFactory.java";

    public static void main(String[] args)
    {
        initSyntaxStrings();

        try ( Scanner fileScan = new Scanner( new File("itemsList.txt")))
        {
            int count = 0;
            String newItemMethod = "";
            String methodName = "";
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

                    Character lowerC = ' ';
                    if(charray.length > 0)
                        lowerC = charray[0];

                    String upperC = lowerC.toString().toUpperCase();
                    String capitalMethod = oneLine.replaceFirst(lowerC.toString() , upperC);
                    methodName = capitalMethod;
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
                    newItemMethod = newItemMethod.replaceAll("\\+", oneLine);

                    file += newItemMethod;
                    // Add item to list, this is the last field for the Item constructor
                    String[] twoParts = file.split("`");
                    String topPart = twoParts[0];
                    System.out.println(twoParts.length);
                    topPart += "`\n\t\titemList.add(new"+ methodName +"());";
                    topPart += twoParts[1];
                    file = topPart;
                    // resets String
                    newItemMethod = syntax;
                }
                count++;
                if(count == 8)
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
                new FileOutputStream(ROGUE_PATH), "utf-8")))
        {
            addAlreadyTypedItems();
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
        file =
                "package items;\n" +
                        "\n" +
                        "import java.awt.Color;\n" +
                        "import java.io.Serializable;\n" +
                        "import java.util.ArrayList;\n" +
                        "import java.util.List;\n" +
                        "import java.util.Random;\n" +
                        "\n" +
                        "import asciiPanel.AsciiPanel;\n" +
                        "import wolrdbuilding.Palette;\n" +
                        "import wolrdbuilding.World;\n" +
                        "\n" +
                        "public class ItemFactory implements Serializable\n" +
                        "{\n" +
                        "\tprivate World world;\n" +
                        "\tpublic Random r;\n" +
                        "\tprivate List<Item> itemList;\n" +
                        "\t\n" +
                        "\tpublic ItemFactory(World world)\n" +
                        "\t{\n" +
                        "\t\tthis.world = world;\n" +
                        "\t\tthis.r = new Random();\n" +
                        "\t\tinitItemList();\n" +
                        "\t}\n" +
                        "\tpublic ItemFactory()\n" +
                        "\t{\n" +
                        "\t\tthis.world = null;\n" +
                        "\t\tthis.r = new Random();\n" +
                        "\t\tinitItemList();\n" +
                        "\t}\n" +
                        "\tpublic Item getRandomItem()\n" +
                        "\t{\n" +
                        "\t\treturn itemList.get(r.nextInt(itemList.size()));\n" +
                        "\t}\n" +
                        "\tpublic void initItemList()\n" +
                        "\t{\n" +
                        "\t\titemList = new ArrayList<Item>();\n" +
                        "\t\t//`\n" +
                        "\t}\n";
        refSyntax =
                "\tpublic Item newScopedRifle()\n" +
                        "\t{\n" +
                        "\t\tItem scopedRifle = new Item((char)234, Palette.red, Type.GUN,\n" +
                        "\t\t\t\t\"Scoped Rifle\", \n" +
                        "\t\t\t\t\"A classic rifle with a good ranged scope. Will kill an enemy form far away while keeping you out of danger.\",\n" +
                        "\t\t\t\t50,\n" +
                        "\t\t\t\tItem.Rarity.UNCOMMON);\n" +
                        "\t\tif(world != null)\n" +
                        "\t\t\tworld.spawnInside(r.nextInt(5), scopedRifle);\n" +
                        "\t\treturn scopedRifle;\n" +
                        "\t}";
        syntax =
                "\tpublic Item new@()\n" +
                        "\t{\n" +
                        "\t\tItem @ = new Item((char)#, Palette.$, Type.%,\n" +
                        "\t\t\t\t\"^\", \n" +
                        "\t\t\t\t\"&\",\n" +
                        "\t\t\t\t*,\n" +
                        "\t\t\t\tItem.Rarity.+);\n" +
                        "\t\tif(world != null)\n" +
                        "\t\t\tworld.spawnInside(r.nextInt(5), @);\n" +
                        "\t\treturn @;\n" +
                        "\t}\n";
    }
    /*
        Instead of retyping these into the item file, i simply copied and pasted them here to save time.
        Work smarter, not harder.
     */
    private static void addAlreadyTypedItems()
    {
        file += "";
    }
}
