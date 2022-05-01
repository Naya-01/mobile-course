import 'dart:convert';
import 'dart:developer' as developer;
import 'dart:math';
import 'package:flutter/foundation.dart';
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
  late Future<List<Film>> futureListFilm;

  @override
  void initState() {
    super.initState();
    futureListFilm = _getAllFilms(http.Client());
  }

  // A function that converts a response body into a List<Film>.
  List<Film> parseFilms(String responseBody) {
    final parsed = jsonDecode(responseBody);
    return parsed.map<Film>((json) => Film.fromJson(json)).toList();
  }

  Future<List<Film>> _getAllFilms(http.Client client) async {
    final response =
        await client.get(Uri.parse('https://ghibliapi.herokuapp.com/films'));
    // Use the compute function to run parseFilms in a separate isolate.
    return compute(parseFilms, response.body);
    //return parseFilms(response.body); // if we were not to use an Isolate
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
            FutureBuilder<List<Film>>(
              future: _getAllFilms(http.Client()),
              builder: (context, snapshot) {
                if (snapshot.hasData) {
                  return Expanded(
                      child: Column(
                        children: [
                          Expanded(
                              child: SingleChildScrollView(
                                scrollDirection: Axis.vertical,
                                child: Text(snapshot.data.toString() + snapshot.data.toString()),
                              ),
                          )
                        ],
                     )
                  );
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
    );
  }
}
