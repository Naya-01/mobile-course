import 'dart:html';
import 'dart:ui';

import 'package:fexo3/ui/models/FilmModel.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';


class FilmCard extends StatefulWidget {
  final int id;
  final String date;
  final String title;
  final String score;
  final String time;
  final String director;
  final String description;
  final String image;
  const FilmCard({
    Key? key,
    required this.id,
    required this.date,
    required this.title,
    required this.score,
    required this.time,
    required this.director,
    required this.description,
    required this.image,
  }) : super(key: key);

  @override
  State<FilmCard> createState() => _FilmCard();
}

class _FilmCard extends State<FilmCard> {
  @override
  Widget build(BuildContext context) {
    return Center(
      child: Card(
        child: InkWell(
          splashColor: Colors.blue.withAlpha(30),
          onTap: () {
            Provider.of<FilmModel>(context, listen: false).changeBoolean(widget.id);
          },
          child: Consumer<FilmModel> (
            builder: (context, film, child){
              return SizedBox(
                height: 300,
                child: Row(
                  children: [
                    Center(
                      child: Padding(
                          padding: const EdgeInsets.all(3),
                          child: Column(
                            children: [
                              Expanded(
                                child: Image.network(widget.image),
                                flex: 2,
                              ),
                            ],
                          )
                      ),
                    ),
                    Expanded(
                      child: Container(
                        alignment: Alignment.topLeft,
                        child: Column(
                          children: [
                            Expanded(
                              child: ListTileTheme(
                                  contentPadding: const EdgeInsets.all(3),
                                  child: Align(
                                      alignment: Alignment.centerLeft,
                                      child: Column(
                                        children: [
                                          Expanded(
                                            child: Column(
                                              crossAxisAlignment: CrossAxisAlignment.start,
                                              children: [
                                                Text(widget.date,style: const TextStyle(
                                                    color: Colors.grey)),
                                                const Padding(
                                                    padding: EdgeInsets.only(
                                                        top: 10.0, bottom: 10.0)),
                                                Text(widget.title,
                                                    style: const TextStyle(
                                                        color: Colors.black)),
                                                const Padding(
                                                    padding: EdgeInsets.only(
                                                        top: 10.0, bottom: 10.0)),
                                                Text("${widget.score}%",
                                                    style: const TextStyle(
                                                        color: Colors.red)),
                                                const Padding(
                                                    padding: EdgeInsets.only(
                                                        top: 10.0, bottom: 10.0)),
                                                if(film.listBool[widget.id])  ...[
                                                  Expanded(
                                                    child: Column(
                                                      crossAxisAlignment: CrossAxisAlignment.start,
                                                      children: [
                                                        Text("${widget.time} minutes",style: const TextStyle(
                                                            color: Colors.blue)),
                                                        const Padding(
                                                            padding: EdgeInsets.only(
                                                                top: 10.0, bottom: 10.0)),
                                                        Text(widget.director,
                                                            style: const TextStyle(
                                                                color: Colors.blue)),
                                                        const Padding(
                                                            padding: EdgeInsets.only(
                                                                top: 10.0, bottom: 10.0)),
                                                        Expanded(
                                                            child: Text(widget.description)
                                                        )
                                                      ],
                                                    ),
                                                  )
                                                ]
                                              ],
                                            ),
                                          )
                                        ],
                                      )
                                  )),
                            ),
                            // if(bool[widget.id]) ...[
                            //   Expanded(
                            //     child: ListTileTheme(
                            //         textColor: Colors.grey,
                            //         contentPadding: const EdgeInsets.all(3),
                            //         child: Align(
                            //             alignment: Alignment.centerLeft,
                            //             child: Column(
                            //               children: [
                            //                 Expanded(
                            //                   child: Column(
                            //                     crossAxisAlignment: CrossAxisAlignment.start,
                            //                     children: [
                            //                       Text("${widget.time} minutes",style: const TextStyle(
                            //                           color: Colors.blue)),
                            //                       const Padding(
                            //                           padding: EdgeInsets.only(
                            //                               top: 10.0, bottom: 10.0)),
                            //                       Text(widget.director,
                            //                           style: const TextStyle(
                            //                               color: Colors.blue)),
                            //                       const Padding(
                            //                           padding: EdgeInsets.only(
                            //                               top: 10.0, bottom: 10.0)),
                            //                       Expanded(
                            //                           child: Text(widget.description)
                            //                       )
                            //                     ],
                            //                   ),
                            //                 )
                            //               ],
                            //             )
                            //         )),
                            //   ),
                            // ]

                          ],
                        ),
                      ),
                    ),
                  ],
                ),
              );
            },
        )
        ),
        elevation: 8,
        margin: EdgeInsets.all(10),
      ),
    );
  }
}

