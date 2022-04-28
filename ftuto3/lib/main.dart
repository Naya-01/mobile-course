import 'dart:convert';
import 'dart:developer' as developer;
import 'dart:math';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:html_unescape/html_unescape.dart';

import 'domain/Film.dart';

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
  late Future<Film> futureFilm;



  @override
  void initState() {
    super.initState();
    futureFilm = _getOneFilm(Random().nextInt(MOVIE_COUNT) + 1);
  }
  _catchOneFilm() async {
    var movie = await _getOneFilm(Random().nextInt(MOVIE_COUNT) + 1);
    setState(() {
      _movieToShow = movie.title;
    });
  }

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
            FutureBuilder<Film>(
              future: futureFilm,
              builder: (context, snapshot) {
                if (snapshot.hasData) {
                  return Text(snapshot.data!.title);
                } else if (snapshot.hasError) {
                  return Text('${snapshot.error}');
                }
                // By default, show a loading spinner.
                return const CircularProgressIndicator();
              },
            )

          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () async {
          try {
            _catchOneFilm;
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

Future<Film> _getOneFilm([int id = 1]) async { // UPDATE
  try {
    await Future.delayed(
        Duration(seconds: 2)); //New code
    var unescape = HtmlUnescape();
    var response = await http
        .get(Uri.parse('https://film-api-vinci.herokuapp.com/films/$id'));
    if (response.statusCode == 200) {
      return Film.fromJson(jsonDecode(unescape.convert(response.body)));
    } else
      throw Exception("Failed to load the movie! \nStatus code :${response.statusCode}"); // UPDATE
      } catch (err) {
      developer.log('Exception in _getOneFilm : $err');
      rethrow;
    }
  }
