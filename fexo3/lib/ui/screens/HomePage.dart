import 'package:fexo3/ui/models/FilmModel.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../../domain/Film.dart';
import '../../services/FilmsService.dart';
import 'FilmCard.dart';

class MyHomePage  extends StatefulWidget {
  const MyHomePage ({Key? key}) : super(key: key);

  @override
  State<MyHomePage > createState() => _MyHomePage();
}

class _MyHomePage  extends State<MyHomePage > {

  @override
  Widget build(BuildContext context) {
    return  Scaffold(
      appBar: AppBar(
        title: const Text("Films Screen"),
        actions: [
          IconButton(
            icon: const Icon(Icons.expand),
            tooltip: 'Expand movies description',
            onPressed: () {
              Provider.of<FilmModel>(context, listen: false).changeAllBoolean();
            },
          ),
        ],
      ),
      body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Expanded(
                  child: FutureBuilder<List<Film>>(
                    future: Provider.of<FilmModel>(context, listen: false).getAllFilms,
                    builder: (context, snapshot) {
                      if (snapshot.hasData) {
                        Provider.of<FilmModel>(context, listen: false).initBooleanList(snapshot.data!.length);
                        return ListView.builder(
                          shrinkWrap: true,
                          itemCount: snapshot.data!.length,
                          itemBuilder: (context, index){
                            print(index);
                            return FilmCard(
                                id: index,
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
          )
      ),
    );
  }
}
