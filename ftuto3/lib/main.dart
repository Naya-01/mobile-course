import 'dart:developer' as developer;
import 'dart:math';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);
  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  String _movieToShow = "";
  static const MOVIE_COUNT = 9;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            const Text(
              'Movie you fetched:',
            ),
            Text(
              '$_movieToShow',
              style: Theme
                  .of(context)
                  .textTheme
                  .headline4,
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () async {
          try {
            var filmInsteadOfCounter =
            await _getOneFilm(Random().nextInt(MOVIE_COUNT) + 1);
            setState(() {
              _movieToShow = filmInsteadOfCounter;
            });
          } catch (e) {
            developer.log('Exception in onPressed : $e');
          }
        },
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ),
    );
  }
}

Future<String> _getOneFilm([int id = 1]) async {
  try {
    var response = await http
        .get(Uri.parse('https://film-api-vinci.herokuapp.com/films/$id'));
    if (response.statusCode == 200)
      return response.body.toString();
    else
      return "Failed to load the movie! \nStatus code :${response.statusCode}";
  } catch (err) {
    developer.log('Exception in _getOneFilm : $err');
    rethrow;
  }
}