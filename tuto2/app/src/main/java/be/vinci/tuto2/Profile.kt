package be.vinci.tuto2

private const val lipsum = "\n" +
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla consectetur nunc ante, condimentum pretium dolor ornare sed. Vestibulum ligula felis, interdum a auctor a, tempor quis lorem. Nunc dictum risus in odio porta efficitur. Nullam congue sapien vel aliquam semper. Fusce a fermentum magna, in dictum risus. Sed eleifend bibendum dui et finibus. Curabitur elementum nibh ac porta malesuada. Donec in consequat nisi. Nulla gravida ipsum massa, tempus cursus lectus hendrerit vel. Etiam ullamcorper pellentesque tincidunt.\n" +
        "\n" +
        "Proin venenatis venenatis diam, ut euismod ipsum sodales at. Cras consectetur, magna quis scelerisque dignissim, dolor neque fermentum metus, vel fermentum eros lectus vel nunc. Praesent et faucibus eros. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Mauris dui tortor, venenatis vel dolor et, egestas semper risus. Vestibulum mollis velit a tortor sagittis luctus. Morbi a sapien felis. Sed condimentum tellus mauris, quis imperdiet lectus faucibus sed. Pellentesque accumsan, leo imperdiet efficitur venenatis, risus eros pulvinar turpis, eu hendrerit ipsum mauris id elit. Quisque vel aliquam metus.\n" +
        "\n" +
        "Nulla viverra, nisi sed iaculis mollis, nulla turpis elementum diam, a finibus turpis nisl non sapien. Pellentesque fringilla urna et scelerisque convallis. Praesent congue, turpis ac sodales tempor, libero massa varius est, id scelerisque massa tortor et libero. Vivamus eu commodo magna. Donec in volutpat nibh. Vestibulum rhoncus blandit posuere. Vestibulum mattis est id euismod congue. Nulla euismod libero vitae odio auctor eleifend. Curabitur congue efficitur quam vel cursus."
data class Profile(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val description: String = lipsum,
)
fun getProfiles() = listOf(
    Profile(1, "Gary", "Petersen", 50),
    Profile(2, "Matthew", "Richardson", 51),
    Profile(3, "Mavis", "Wilson", 58),
    Profile(4, "Mary", "Sanders", 68),
    Profile(5, "Bob", "Walker", 77),
    Profile(6, "Judy", "Jacks", 30),
    Profile(7, "Thomas", "Sawyer", 65),
    Profile(8, "Dwight", "Slama", 60),
    Profile(9, "Francis", "Perkins", 42),
    Profile(10, "Kala", "Kelly", 40),
    Profile(11, "Brenda", "Garcia", 64),
    Profile(12, "Denyse", "Jacobs", 72),
)