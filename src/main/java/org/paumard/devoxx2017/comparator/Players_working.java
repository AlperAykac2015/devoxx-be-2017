jshell> record PlayerRecord(String displayName, String nickName, int gameCount, double rate)  implements Comparable<PlayerRecord>{
   ...>     @Override
   ...>     public int compareTo(PlayerRecord other) {
   ...>         if (this.nickName == null && other.nickName == null) {
   ...>             return 0;
   ...>         } else if (this.nickName == null) {
   ...>             return -1;
   ...>         } else if (other.nickName == null) {
   ...>             return 1;
   ...>         } else {
   ...>             return this.nickName.compareTo(other.nickName);
   ...>         }
   ...>     }
   ...> }
   ...>

jshell> List<PlayerRecord> players = new ArrayList<>();
   ...>         Random random = new Random();
   ...>
   ...>         // Sample real person names
   ...>         String[] names = {"John Doe", "Jane Smith", "Michael Brown", "Emily White", "Daniel Harris",
   ...>                           "Laura Green", "David Johnson", "Emma Jones", "Christopher Moore", "Sarah Taylor",
   ...>                           "James Wilson", "Chloe King", "Benjamin Wright", "Sophia Lee", "Matthew Scott",
   ...>                           "Olivia Hall", "Alexander Young", "Isabella Adams", "Joseph Thompson", "Charlotte Lewis"};
   ...>
   ...>         // Sample animal names for nicknames
   ...>         String[] animalNicknames = {"Lion", "Tiger", "Bear", "Wolf", "Fox", "Hawk", "Eagle", "Falcon", "Shark", "Panther",
   ...>                                     "Leopard", "Snake", "Dragon", "Lion", "Turtle", "Bear", "Deer", "Bear", "Monkey", "Dolphin"};
   ...>
   ...>         // Generate 20 players with animal nicknames
   ...>         for (int i = 0; i < 20; i++) {
   ...>             players.add(new PlayerRecord(
   ...>                 names[i % names.length],
   ...>                 animalNicknames[i % animalNicknames.length],
   ...>                 random.nextInt(100), // Assuming gameCount is a random integer up to 100
   ...>                 random.nextDouble() * 5 // Assuming rate is a random double up to 5
   ...>             ));
   ...>         }
   ...>
   ...>         // Generate 10 players with no nicknames
   ...>         for (int i = 20; i < 30; i++) {
   ...>             players.add(new PlayerRecord(
   ...>                 names[i % names.length],
   ...>                 null, // or "" for empty
   ...>                 random.nextInt(100), // Assuming gameCount is a random integer up to 100
   ...>                 random.nextDouble() * 5 // Assuming rate is a random double up to 5
   ...>             ));
   ...>         }
   ...>
   ...>         // Print the generated players
   ...>         players.forEach(player -> System.out.println(player));


   jshell> players.sort(Comparator.comparing(PlayerRecord::nickName,nullsFirst(Comparator.naturalOrder())))

   jshell> players.sort(Comparator.comparing(PlayerRecord::nickName,nullsFirst(Comparator.naturalOrder())).thenComparing(PlayerRecord::displayName))