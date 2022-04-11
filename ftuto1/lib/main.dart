import 'package:english_words/english_words.dart';
import 'package:flutter/material.dart';
import 'package:ftuto1/my_oeschinen_card.dart';
import 'package:ftuto1/my_card.dart';


const places = [
  {
    "title1": "Lausanne",
    "title2": "Geneva lake",
    "description": "The sun is..."
  },
  {"title1": "Bern", "title2": "Place fédérale"},
  {
    "title1": "Aigle",
    "title2": "Le château entouré de vignes",
    "descriptin": "Il était une fois..."
  }
];


void main() {
  runApp(const MyApp());
}

Widget titleSection = Container(
  padding: const EdgeInsets.all(32),
  child: Row(
    children: [
      Expanded(
        /*1*/
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            /*2*/
            Container(
              padding: const EdgeInsets.only(bottom: 8),
              child: const Text(
                'Oeschinen Lake Campground',
                style: TextStyle(
                  fontWeight: FontWeight.bold,
                ),
              ),
            ),
            Text(
              'Kandersteg, Switzerland',
              style: TextStyle(
                color: Colors.grey[500],
              ),
            ),
          ],
        ),
      ),
      /*3*/
      Icon(
        Icons.star,
        color: Colors.red[500],
      ),
      const Text('41'),
    ],
  ),
);

Column _buildButtonColumn(Color color, IconData icon, String label) {
  return Column(
    mainAxisSize: MainAxisSize.min,
    mainAxisAlignment: MainAxisAlignment.center,
    children: [
      Icon(icon, color: color),
      Container(
        margin: const EdgeInsets.only(top: 8),
        child: Text(
          label,
          style: TextStyle(
            fontSize: 12,
            fontWeight: FontWeight.w400,
            color: color,
          ),
        ),
      ),
    ],
  );
}

Widget textSection = const Padding(
  padding: EdgeInsets.all(32),
  child: Text(
    'Lake Oeschinen lies at the foot of the Blüemlisalp in the Bernese '
    'Alps. Situated 1,578 meters above sea level, it is one of the '
    'larger Alpine Lakes. A gondola ride from Kandersteg, followed by a '
    'half-hour walk through pastures and pine forest, leads you to the '
    'lake, which warms to 20 degrees Celsius in the summer. Activities '
    'enjoyed here include rowing, and riding the summer toboggan run.',
    softWrap: true,
  ),
);

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    var allCardNoCards = List.generate(
      // type is inferred (List<Widget>)
        10,
            (index) =>
            MyCard(
              title1: "Card N°$index",
            ));
    final allPlacesCards =
    []; // type is inferred (List<dynamic>) and the variable is final
    //(single assignment)
    for (var i = 1; i <= 10; ++i) {
      allPlacesCards.add(MyCard(
        title1: "Amazing place N°$i",
      ));
    }
    final swissCards = places.map((location) =>
        MyCard(
            title1: location["title1"] ?? "Title1 TBD",
            title2: location["title2"] ?? "Title2 TBD",
            description: location["description"],
            imageUri: location["imageUri"] ?? "images/default.jpg")).toList();
// type would be iterable<MyCards> without toList()
    return MaterialApp(
      title: 'Flutter layout demo',
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Flutter layout demo'),
        ),
        body: ListView(
            children: [...allCardNoCards, ...allPlacesCards, ...swissCards]
          // instead of "Spread operator" "..." to concatenate three lists,
          // allCardNoCards + allPlacesCards + swissCards could be called
          // if the three lists were of the same type !
          // New code,
        ),
      ),
    );
  }
}

class RandomsWords extends StatefulWidget {
  const RandomsWords({Key? key}) : super(key: key);

  @override
  State<RandomsWords> createState() => _RandomsWordsState();
}

class _RandomsWordsState extends State<RandomsWords> {
  @override
  Widget build(BuildContext context) {
    final _suggestions = <WordPair>[];
    const _biggerFont = TextStyle(fontSize: 18.0);
    return Scaffold(
      appBar: AppBar(
        title: const Text('Startup Name Generator'),
      ),
      body: ListView.builder(
        padding: const EdgeInsets.all(16.0),
        itemBuilder: (context, i) {
          if (i.isOdd) return const Divider();
          final index = i ~/ 2;

          if (index >= _suggestions.length) {
            _suggestions.addAll(generateWordPairs().take(10));
          }

          return ListTile(
            title: Text(
              _suggestions[index].asPascalCase,
              style: _biggerFont,
            ),
            textColor: Colors.blue,
            tileColor: Colors.white38,
          );
        },
      ),
    );
  }
}
