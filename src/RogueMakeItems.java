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
    private static final String ROGUE_PATH = "D:\\06 SOURCE\\01 JAVA PROJECTS\\Rogue One\\src\\items\\ItemFactory.java";

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
                    Character lowerC = charray[0];
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
                    newItemMethod = newItemMethod.replaceAll("~", oneLine);
                }
                else if(count == 8)
                {
                    newItemMethod = newItemMethod.replaceAll("\\+", oneLine);

                    file += newItemMethod;
                    // Add item to list
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
                        "import items.Item.Type;\n" +
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
                        "\t\titemList.add(newMiningBeam());\n" +
                        "\t}\n";
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
                        "\t\tItem @ = new Item((char)#, AsciiPanel.$, Type.%,\n" +
                        "\t\t\t\t\"^\", \n" +
                        "\t\t\t\t\"&\",\n" +
                        "\t\t\t\t*, ~\n" +
                        "\t\t\t\t, +);\n" +
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
        file +=
                "\t/*\n" +
                        "\t *\t\tStealth Items --------------------------------------------\n" +
                        "\t */\n" +
                        "\tpublic Item newInvisibilityCloak()\n" +
                        "\t{\n" +
                        "\t\tItem cloak = new Item((char)131, Palette.blue, Type.STEALTH ,\n" +
                        "\t\t\t\t\"Loin Cloak\", \n" +
                        "\t\t\t\t\"It's better than being seen!\",\n" +
                        "\t\t\t\t0, 200\n" +
                        "\t\t\t\t, 50);\n" +
                        "\t\tif(world != null)\n" +
                        "\t\t\tworld.spawnInside(r.nextInt(5), cloak);\n" +
                        "\t\treturn cloak;\n" +
                        "\t}\n" +
                        "\tpublic Item newInvisibilityChaps()\n" +
                        "\t{\n" +
                        "\t\tItem cloak = new Item((char)131, AsciiPanel.brightBlue, Type.STEALTH ,\n" +
                        "\t\t\t\t\"Loin Chaps\", \n" +
                        "\t\t\t\t\"Good for a late night dance party, or when running with the wolves.\",\n" +
                        "\t\t\t\t0, 200\n" +
                        "\t\t\t\t, 50);\n" +
                        "\t\tif(world != null)\n" +
                        "\t\t\tworld.spawnInside(r.nextInt(5), cloak);\n" +
                        "\t\treturn cloak;\n" +
                        "\t}\n" +
                        "\t\n" +
                        "\t/*\n" +
                        "\t *\t\tHead Items --------------------------------------------\n" +
                        "\t */\n" +
                        "\tpublic Item newRuggedCap()\n" +
                        "\t{\n" +
                        "\t\tItem ruggedCap = new Item((char)131, AsciiPanel.brightBlue, Type.HEAD ,\n" +
                        "\t\t\t\t\"Rugged Cap\", \n" +
                        "\t\t\t\t\"It's like wearing a rug on your head!\",\n" +
                        "\t\t\t\t0, 200\n" +
                        "\t\t\t\t, 250);\n" +
                        "\t\tif(world != null)\n" +
                        "\t\t\tworld.spawnInside(r.nextInt(5), ruggedCap);\n" +
                        "\t\treturn ruggedCap;\n" +
                        "\t}\n" +
                        "\t/*\n" +
                        "\t *\t\tTorso Items --------------------------------------------\n" +
                        "\t */\n" +
                        "\tpublic Item newLoinCloth()\n" +
                        "\t{\n" +
                        "\t\tItem loinCloth = new Item((char)131, AsciiPanel.brightBlue, Type.TORSO ,\n" +
                        "\t\t\t\t\"Loin Cloth\", \n" +
                        "\t\t\t\t\"It's better than being naked!\",\n" +
                        "\t\t\t\t0, 200\n" +
                        "\t\t\t\t, 250);\n" +
                        "\t\tif(world != null)\n" +
                        "\t\t\tworld.spawnInside(r.nextInt(5), loinCloth);\n" +
                        "\t\treturn loinCloth;\n" +
                        "\t}\n" +
                        "\t/*\n" +
                        "\t *\t\tArm Items --------------------------------------------\n" +
                        "\t */\n" +
                        "\tpublic Item newRacingGloves()\n" +
                        "\t{\n" +
                        "\t\tItem racingGloves = new Item((char)131, AsciiPanel.brightBlue, Type.ARMS,\n" +
                        "\t\t\t\t\"Racing Gloves\", \n" +
                        "\t\t\t\t\"It's like wearing a rug on your head!\",\n" +
                        "\t\t\t\t0, 200\n" +
                        "\t\t\t\t, 250);\n" +
                        "\t\tif(world != null)\n" +
                        "\t\t\tworld.spawnInside(r.nextInt(5), racingGloves);\n" +
                        "\t\treturn racingGloves;\n" +
                        "\t}\n" +
                        "\t/*\n" +
                        "\t *\t\tLeg Items --------------------------------------------\n" +
                        "\t */\n" +
                        "\tpublic Item newDankBoots()\n" +
                        "\t{\n" +
                        "\t\tItem dankBoots = new Item((char)131, AsciiPanel.brightBlue, Type.LEGS ,\n" +
                        "\t\t\t\t\"Dank Boots\", \n" +
                        "\t\t\t\t\"It's like wearing a rug on your head!\",\n" +
                        "\t\t\t\t0, 200\n" +
                        "\t\t\t\t, 250);\n" +
                        "\t\tif(world != null)\n" +
                        "\t\t\tworld.spawnInside(r.nextInt(5), dankBoots);\n" +
                        "\t\treturn dankBoots;\n" +
                        "\t}\n" +
                        "\t/*\n" +
                        "\t *\t\tGUN Items --------------------------------------------\n" +
                        "\t */\n" +
                        "\tpublic Item newMusketGun()\n" +
                        "\t{\n" +
                        "\t\tItem musketGun = new Item((char)131, AsciiPanel.brightBlue, Type.GUN ,\n" +
                        "\t\t\t\t\"Musket Gun\", \n" +
                        "\t\t\t\t\"Another revistited classic, the musket is slow to shoot, and slow to kill.\"\n" +
                        "\t\t\t\t+ \"It's slow and small rounds don't even damage the loot your soon to be slain enemy\"\n" +
                        "\t\t\t\t+ \"is carrying. The musket is often inprecise and can be of a tile or two.\"\n" +
                        "\t\t\t\t+\" It surely is a skill gun though.\",\n" +
                        "\t\t\t\t200, 0\n" +
                        "\t\t\t\t, 250);\n" +
                        "\t\tif(world != null)\n" +
                        "\t\t\tworld.spawnInside(r.nextInt(5), musketGun);\n" +
                        "\t\treturn musketGun;\n" +
                        "\t}\n" +
                        "\tpublic Item newScopedRifle()\n" +
                        "\t{\n" +
                        "\t\tItem scopedRifle = new Item((char)131, AsciiPanel.brightBlue, Type.GUN ,\n" +
                        "\t\t\t\t\"Scoped Rifle\", \n" +
                        "\t\t\t\t\"A classic hunting rifle, with a proper 8x scope, quick bullet travel and \"\n" +
                        "\t\t\t\t+ \"deals absolutely massive damage. Hard to use, but quite the reward when you make the shot.\"\n" +
                        "\t\t\t\t+ \" Good for killing off dazed mutants from far awar, or getting those clumsy malfunctioned \"\n" +
                        "\t\t\t\t+\" maintence droids clear of your path ahead. Good luck with using this thing\"\n" +
                        "\t\t\t\t+\", it's bound to attract others attention.\",\n" +
                        "\t\t\t\t200, 0\n" +
                        "\t\t\t\t, 250);\n" +
                        "\t\tif(world != null)\n" +
                        "\t\t\tworld.spawnInside(r.nextInt(5), scopedRifle);\n" +
                        "\t\treturn scopedRifle;\n" +
                        "\t}\n" +
                        "\tpublic Item newMacroUzi()\n" +
                        "\t{\n" +
                        "\t\tItem macroUzi = new Item((char)131, AsciiPanel.brightBlue, Type.GUN ,\n" +
                        "\t\t\t\t\"Macro Uzi\", \n" +
                        "\t\t\t\t\"The sucessor of the beloved micro, the macro is everthing that the micrco was, \"\n" +
                        "\t\t\t\t+ \"but in a completely macro way. Macro stock, macro compensator, and macro skins.\"\n" +
                        "\t\t\t\t+ \"Using temporal technologies, engineers were able to fit everything extra that\"\n" +
                        "\t\t\t\t+\" the macro produced into the body of a micro. It has the classic look and feel but\"\n" +
                        "\t\t\t\t+\" that macro will kick you on your ass if you're not ready for it.\",\n" +
                        "\t\t\t\t100, 0\n" +
                        "\t\t\t\t, 250);\n" +
                        "\t\tif(world != null)\n" +
                        "\t\t\tworld.spawnInside(r.nextInt(5), macroUzi);\n" +
                        "\t\treturn macroUzi;\n" +
                        "\t}\n" +
                        "\tpublic Item newSmartSword()\n" +
                        "\t{\n" +
                        "\t\tItem smartSword = new Item('\\\\', AsciiPanel.brightBlue, Type.GUN ,\n" +
                        "\t\t\t\t\"Smart Sword\", \n" +
                        "\t\t\t\t\"You've come across a 5th generation smart sword, equipped with auto beheading algorithms\"\n" +
                        "\t\t\t\t+ \" and boomerang functionality, this smart sword is good for close quarters combat along with\"\n" +
                        "\t\t\t\t+ \" ranged combat as well.\",\n" +
                        "\t\t\t\t100, 100\n" +
                        "\t\t\t\t,250);\n" +
                        "\t\tif(world != null)\n" +
                        "\t\t\tworld.spawnInside(r.nextInt(5), smartSword);\n" +
                        "\t\treturn smartSword;\n" +
                        "\t}\n" +
                        "\tpublic Item newDevSword()\n" +
                        "\t{\n" +
                        "\t\tItem devSword = new Item('|', AsciiPanel.brightBlue, Type.GUN ,\n" +
                        "\t\t\t\t\"Dev Sword\", \n" +
                        "\t\t\t\t\"This is the almighty and powerfull, totally not overpowerd, completely super easy to find and weild Developemental tool.\"\n" +
                        "\t\t\t\t+ \"It is said thay many men fear anyone who wields this beast. They will pee there pants as they see you and will still be\"\n" +
                        "\t\t\t\t+ \" too scared to change them even if they get back home!\",\n" +
                        "\t\t\t\t100, 100\n" +
                        "\t\t\t\t,10000);\n" +
                        "\n" +
                        "\t\treturn devSword;\n" +
                        "\t}\n" +
                        "\tpublic Item newWinchester2194()\n" +
                        "\t{\n" +
                        "\t\tItem winchester2194 = new Item('+', Color.white, Type.GUN ,\n" +
                        "\t\t\t\t\"Winchester 2194\", \n" +
                        "\t\t\t\t\"The 300 year annivesary edition of a classic, the Winchester Model 1894 hunting rifle.\"\n" +
                        "\t\t\t\t+ \" You can see this particular one if quite old, beat up, and in need of some cleaning.\"\n" +
                        "\t\t\t\t+ \" It'd be worth doing so since these are know for the longetivity.. \",\n" +
                        "\t\t\t\t0, 0\n" +
                        "\t\t\t\t, 1000);\n" +
                        "\t\tif(world != null)\n" +
                        "\t\t\tworld.spawnInside(r.nextInt(5), winchester2194);\n" +
                        "\t\t\n" +
                        "\t\treturn winchester2194;\n" +
                        "\t}\n" +
                        "\t// Viles //\n" +
                        "\tpublic Item newVileOfNanobots()\n" +
                        "\t{\n" +
                        "\t\tItem vileOfNanobots = new Item('v', Color.white, Type.GUN ,\n" +
                        "\t\t\t\t\"Vile Of Nanobots\", \n" +
                        "\t\t\t\t\"A small diamond vile filled with a healthy dose of little nano critters.\"\n" +
                        "\t\t\t\t+ \" Be cautious about breaking one of these; who knows what some loose nanobots can\"\n" +
                        "\t\t\t\t+ \" do when let loose in your inventory. \",\n" +
                        "\t\t\t\t0, 0\n" +
                        "\t\t\t\t, 1000);\n" +
                        "\t\tif(world != null)\n" +
                        "\t\t\tworld.spawnInside(r.nextInt(5), vileOfNanobots);\n" +
                        "\t\t\n" +
                        "\t\treturn vileOfNanobots;\n" +
                        "\t\t\n" +
                        "\t}\n" +
                        "\tpublic Item newNeuralVile()\n" +
                        "\t{\n" +
                        "\t\tItem neuralVile = new Item('`', Color.white, Type.GUN ,\n" +
                        "\t\t\t\t\"Neural Vile\", \n" +
                        "\t\t\t\t\"Filled with dopamines, sugars, vitamins, minerals and stem matter for a boost to nyour vitals.\"\n" +
                        "\t\t\t\t+ \" Be carefull about taking too much or you might be able to develope a dependency. These aren't too \"\n" +
                        "\t\t\t\t+ \" easy to find either.\",\n" +
                        "\t\t\t\t0, 0\n" +
                        "\t\t\t\t, 200);\n" +
                        "\t\tif(world != null)\n" +
                        "\t\t\tworld.spawnInside(r.nextInt(5), neuralVile);\n" +
                        "\t\t\n" +
                        "\t\treturn neuralVile;\n" +
                        "\t}\n" +
                        "\t// Devices // Used to gain an edge in playing\n" +
                        "\tpublic Item newWallBomb()\n" +
                        "\t{\n" +
                        "\t\tItem wallBomb = new Item('*', Color.cyan, Type.DEVICE ,\n" +
                        "\t\t\t\t\"Wall Bomb\", \n" +
                        "\t\t\t\t\"Stuck in a room with no doors? Grab this handy\"\n" +
                        "\t\t\t\t+ \"  wall bomb and place it oewhere. It will digg aa tunnel for you!\",\n" +
                        "\t\t\t\t0, 0\n" +
                        "\t\t\t\t, 10);\n" +
                        "\t\tif(world != null)\n" +
                        "\t\t\tworld.spawnInside(r.nextInt(5), wallBomb);\n" +
                        "\t\t\n" +
                        "\t\treturn wallBomb;\n" +
                        "\t}\n" +
                        "\tpublic Item newEnergySiphon()\n" +
                        "\t{\n" +
                        "\t\tItem energySiphon = new Item('`', Color.white, Type.GUN ,\n" +
                        "\t\t\t\t\"Energy Siphon\", \n" +
                        "\t\t\t\t\"A device which allows you to siphon energy form the area around you.\"\n" +
                        "\t\t\t\t+ \" When properly integrated, the siphon, using energy transmitted through the air, \"\n" +
                        "\t\t\t\t+ \"can help you power your devices.\",\n" +
                        "\t\t\t\t0, 0\n" +
                        "\t\t\t\t, 200);\n" +
                        "\t\tif(world != null)\n" +
                        "\t\t\tworld.spawnInside(r.nextInt(5), energySiphon);\n" +
                        "\t\t\n" +
                        "\t\treturn energySiphon;\n" +
                        "\t}\n" +
                        "\tpublic Item newTemporalScanner()\n" +
                        "\t{\n" +
                        "\t\tItem temporalScanner = new Item('~', Color.YELLOW,  Type.GUN ,\n" +
                        "\t\t\t\t\"Temporal Scanner\", \n" +
                        "\t\t\t\t\"This high fidelity device scans the time and space around you, allowing for an acurate\"\n" +
                        "\t\t\t\t+ \" read out of your surrondings. This unit drains energy quickly so you'll have to \"\n" +
                        "\t\t\t\t+ \" have a powerfull enough battery to handle this.\",\n" +
                        "\t\t\t\t0, 0\n" +
                        "\t\t\t\t, 1000);\n" +
                        "\t\tif(world != null)\n" +
                        "\t\t\tworld.spawnInside(r.nextInt(5), temporalScanner);\n" +
                        "\t\t\n" +
                        "\t\treturn temporalScanner;\n" +
                        "\t\t\n" +
                        "\t}\n" +
                        "\n" +
                        "\tpublic Item newMiningBeam()\n" +
                        "\t{\n" +
                        "\t\tItem miningBeam = new Item('=', Color.GRAY,  Type.GUN ,\n" +
                        "\t\t\t\t\"Mining Beam\", \n" +
                        "\t\t\t\t\"Standard issue industrial mining beam. Good for cutting rocks.\",\n" +
                        "\t\t\t\t0, 0\n" +
                        "\t\t\t\t, 20);\n" +
                        "\t\tif(world != null)\n" +
                        "\t\t\tworld.spawnInside(r.nextInt(5), miningBeam);\n" +
                        "\t\t\n" +
                        "\t\treturn miningBeam;\n" +
                        "\t}\n" +
                        "\t\n" +
                        "\t/*\n" +
                        "\t *\t\tAuto Plasma Items --------------------------------------------\n" +
                        "\t */\n" +
                        "\tpublic Item newPlasmaPack()\n" +
                        "\t{\n" +
                        "\t\tItem plasmaPack = new Item((char)253, Color.CYAN,  Type.APLASMA ,\n" +
                        "\t\t\t\t\"Plasma Pack\", \n" +
                        "\t\t\t\t\"A pack of plasma pods, precisely six, good for cracking with the boys\"\n" +
                        "\t\t\t\t+ \" on a hunger starved night when nothing else is around. \"\n" +
                        "\t\t\t\t+ \"If you bring back the pods, you'll get a discount on your next pack!\",\n" +
                        "\t\t\t\t0, 0\n" +
                        "\t\t\t\t, 500);\n" +
                        "\t\tif(world != null)\n" +
                        "\t\t\tworld.spawnInside(r.nextInt(5), plasmaPack);\n" +
                        "\t\treturn plasmaPack;\n" +
                        "\t}\n" +
                        "\tpublic Item newPlasmaPod()\n" +
                        "\t{\n" +
                        "\t\tItem plasmaPod = new Item((char)249, Color.CYAN, Type.APLASMA ,\n" +
                        "\t\t\t\t\"Plasma Pod\", \n" +
                        "\t\t\t\t\"A small plasma pod which will hold your plasma nicely podded up.\"\n" +
                        "\t\t\t\t+ \" Completely not likely to leak in your bag, so don't worry if you're afraid of breaking it.\"\n" +
                        "\t\t\t\t+ \"No warranty, and use at your own risk, you're dealing with plasma for good sakes... !\",\n" +
                        "\t\t\t\t0, 0\n" +
                        "\t\t\t\t, 500);\n" +
                        "\t\tif(world != null)\n" +
                        "\t\t\tworld.spawnInside(r.nextInt(5), plasmaPod);\n" +
                        "\t\treturn plasmaPod;\n" +
                        "\t}\n" +
                        "\tpublic Item newVictoryItem(int depth)\n" +
                        "\t{\n" +
                        "        Item item = new Item('*', AsciiPanel.brightWhite, Type.APLASMA,\n" +
                        "        \t\t\"Teddy Bear\",\n" +
                        "        \t\t\"This is the one thing you need to win the game. Walk to the rrd staircase and you win.\", \n" +
                        "        \t\t0, 0\n" +
                        "        \t\t,100);\n" +
                        "        \n" +
                        "        if(world != null)\n" +
                        "        \tworld.addAtEmptyLocation(depth, item);\n" +
                        "        return item;\n" +
                        "    }\n" +
                        "\t/*\n" +
                        "\t *\t\tPlasma Items --------------------------------------------------\n" +
                        "\t */\n" +
                        "\tpublic Item newPlasmaJuice()\n" +
                        "\t{\n" +
                        "\t\tItem plasmaJuice = new Item((char)5, Color.CYAN, Type.PLASMA ,\n" +
                        "\t\t\t\t\"Temporal Plasma Pack\", \n" +
                        "\t\t\t\t\"This nifty item squeezes a shit ton of plasma into a tiny little fucking box.\"\n" +
                        "\t\t\t\t+ \" Technically it doesn't do that, but it allows you acess to plasma from different points in the \"\n" +
                        "\t\t\t\t+ \"expnsion of the universe. A nifty little device, hold onto it.\",\n" +
                        "\t\t\t\t0, 0\n" +
                        "\t\t\t\t, 500);\n" +
                        "\t\tif(world != null)\n" +
                        "\t\t\tworld.spawnInside(r.nextInt(5), plasmaJuice);\n" +
                        "\t\t\n" +
                        "\t\treturn plasmaJuice;\n" +
                        "\t}\n" +
                        "\t/*\n" +
                        "\t *\t\tPart Items --------------------------------------------------\n" +
                        "\t */\n" +
                        "\tpublic Item newAnimatronicSkeleton()\n" +
                        "\t{\n" +
                        "\t\tItem animatronicSkeleton = new Item('#', Color.GRAY, Type.PART,\n" +
                        "\t\t\t\t\"Animatronic Skeleton\", \n" +
                        "\t\t\t\t\"A carefully crafted, all purpose skeleton used for making makeshift robots of all sizes and shaped.\"\n" +
                        "\t\t\t\t+ \" The center core seems to need some type of fluid to make the skeleton move.\"\n" +
                        "\t\t\t\t+ \" A small slit is visible on teh underside next to the Noki Core logo.\",\n" +
                        "\t\t\t\t0, 0\n" +
                        "\t\t\t\t, 500);\n" +
                        "\t\tif(world != null)\n" +
                        "\t\t\tworld.spawnInside(r.nextInt(5), animatronicSkeleton);\n" +
                        "\t\t\n" +
                        "\t\treturn animatronicSkeleton;\n" +
                        "\t}\n" +
                        "\tpublic Item newVileOfBioReactant()\n" +
                        "\t{\n" +
                        "\t\tItem bioReactant = new Item((char)239, Color.BLUE,  Type.PART,\n" +
                        "\t\t\t\t\"Bio Chemical Reactant\", \n" +
                        "\t\t\t\t\"You've scored a vile of bio chemical mutant reactant which means you'll\"\n" +
                        "\t\t\t\t+ \" be able to  mutate yourself. This stuff is addictive, like tatoos, or heroin, \"\n" +
                        "\t\t\t\t+ \" so make sure you don't mutate yourself to the grave.\",\n" +
                        "\t\t\t\t0, 0\n" +
                        "\t\t\t\t, 500);\n" +
                        "\t\tif(world != null)\n" +
                        "\t\t\tworld.spawnInside(r.nextInt(5), bioReactant);\n" +
                        "\t\treturn bioReactant;\n" +
                        "\t}\n" +
                        "\tpublic Item newHeatShieldShard()\n" +
                        "\t{\n" +
                        "\t\tItem heatShieldShard = new Item((char)239, Color.BLUE,  Type.PART,\n" +
                        "\t\t\t\t\"Heat Shield Shard\", \n" +
                        "\t\t\t\t\"A shard of shield which must have fallen off a scaled industrial heat shield.\"\n" +
                        "\t\t\t\t+ \" These things are rare so hold on to it. Be careful, you might be in trouble \"\n" +
                        "\t\t\t\t+ \"if you get caught with one of those out in the open.\"\n" +
                        "\t\t\t\t+\" With enough of these you'd be able to make yourself a proper heat shield.\",\n" +
                        "\t\t\t\t0, 0\n" +
                        "\t\t\t\t, 500);\n" +
                        "\t\tif(world != null)\n" +
                        "\t\t\tworld.spawnInside(r.nextInt(5), heatShieldShard);\n" +
                        "\t\treturn heatShieldShard;\n" +
                        "\t}\n" +
                        "\tpublic Item newStickOfRam()\n" +
                        "\t{\n" +
                        "\t\tItem stickOfRam = new Item((char)95, Color.BLUE,  Type.PART ,\n" +
                        "\t\t\t\t\"Stick of RAM\", \n" +
                        "\t\t\t\t\"A good ole, standard size, 1 TB stick of ram.\"\n" +
                        "\t\t\t\t+ \"This stck happens to have a stylized red casing, with rgb light hooks, and \"\n" +
                        "\t\t\t\t+ \"various other party mode features. Works well in parellel with another stick of ram.\",\n" +
                        "\t\t\t\t0, 0\n" +
                        "\t\t\t\t, 500);\n" +
                        "\t\tif(world != null)\n" +
                        "\t\t\tworld.spawnInside(r.nextInt(5), stickOfRam);\n" +
                        "\t\treturn stickOfRam;\n" +
                        "\t}\n";
    }
}
