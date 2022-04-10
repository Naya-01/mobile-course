import 'package:english_words/english_words.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      title: 'Startup Name Generator',
      home: RandomsWords(),
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
