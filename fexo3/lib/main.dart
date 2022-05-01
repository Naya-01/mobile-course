import 'dart:convert';
import 'dart:developer' as developer;
import 'dart:math';
import 'package:fexo3/FilmCard.dart';
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
    await Future.delayed(
        Duration(seconds: 3));
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
        child: Expanded(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Expanded(
                  child: FutureBuilder<List<Film>>(
                    future: futureListFilm,
                    builder: (context, snapshot) {
                      if (snapshot.hasData) {
                        return ListView.builder(
                          shrinkWrap: true,
                          itemCount: snapshot.data!.length,
                          itemBuilder: (context, index){
                            return FilmCard(
                                date: snapshot.data![index].release_date,
                                title: snapshot.data![index].title,
                                score: snapshot.data![index].rt_score,
                                time: snapshot.data![index].running_time,
                                director: snapshot.data![index].director,
                                description: snapshot.data![index].description,
                                image: snapshot.data![index].image);
                          },

                        );

                      } else if (snapshot.hasError) {
                        return Text('${snapshot.error}');
                      }
                      // By default, show a loading spinner.
                      return  Image.network('https://image.tmdb.org/t/p/w600_and_h900_bestv2/npOnzAbLh6VOIu3naU5QaEcTepo.jpg');
                    },
                  )
              ),
            ],
          ),
        )
      ),
    );
  }
}
