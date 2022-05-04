import 'package:flutter/material.dart';

import '../../domain/Film.dart';
import '../../services/MoviesRepository.dart';
import 'FilmCard.dart';

class MyHomePage  extends StatefulWidget {
  const MyHomePage ({Key? key}) : super(key: key);

  @override
  State<MyHomePage > createState() => _MyHomePage();
}

class _MyHomePage  extends State<MyHomePage > {

  final MoviesRepository  movies = MoviesRepository();

  late Future<List<Film>> futureListFilm;

  @override
  void initState() {
    super.initState();
    futureListFilm = movies.getAllFilms();
  }
  @override
  Widget build(BuildContext context) {
    return  Scaffold(
      appBar: AppBar(
        title: const Text("Films Screen"),
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
