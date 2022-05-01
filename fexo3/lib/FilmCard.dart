import 'dart:html';
import 'dart:ui';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';


class FilmCard extends StatefulWidget {
  final String date;
  final String title;
  final String score;
  final String time;
  final String director;
  final String description;
  final String image;
  const FilmCard({
    Key? key,
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
  bool myBool=false;

  void _changeBool(){
    setState(() {
      myBool= !myBool;
    });
  }
  @override
  Widget build(BuildContext context) {
    return Center(
      child: Card(
        child: InkWell(
          splashColor: Colors.blue.withAlpha(30),
          onTap: () {
            _changeBool();
          },
          child: SizedBox(
            height: 300,
            child: Row(
              children: [
                Center(
                  child: Padding(
                    padding: const EdgeInsets.all(3),
                    child: Expanded(
                      child: Image.network(widget.image),
                      flex: 2,
                    ),
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
                                  child: Expanded(
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
                                      ],
                                    ),
                                  )
                              )),
                        ),
                        if(myBool) ...[
                          Expanded(
                            child: ListTileTheme(
                                textColor: Colors.grey,
                                contentPadding: const EdgeInsets.all(3),
                                child: Align(
                                    alignment: Alignment.centerLeft,
                                    child: Expanded(
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
                                )),
                          ),
                        ]

                      ],
                    ),
                  ),
                ),
              ],
            ),
          ),
        ),
        elevation: 8,
        margin: EdgeInsets.all(10),
      ),
    );
  }
}


// class FilmCard extends StatelessWidget {
//   final String date;
//   final String title;
//   final String score;
//   final String time;
//   final String director;
//   final String description;
//   final String image;
//
//   const FilmCard({
//     Key? key,
//     required this.date,
//     required this.title,
//     required this.score,
//     required this.time,
//     required this.director,
//     required this.description,
//     required this.image,
//   }) : super(key: key);
//
//   @override
//   Widget build(BuildContext context) {
//     return Center(
//       child: Card(
//         child: InkWell(
//           splashColor: Colors.blue.withAlpha(30),
//           onTap: () {
//             debugPrint('salut salut');
//           },
//           child: SizedBox(
//             height: 300,
//             child: Row(
//               children: [
//                 Center(
//                   child: Padding(
//                     padding: const EdgeInsets.all(3),
//                     child: Expanded(
//                       child: Image.network(image),
//                       flex: 2,
//                     ),
//                   ),
//                 ),
//                 Expanded(
//                   child: Container(
//                     alignment: Alignment.topLeft,
//                     child: Column(
//                       children: [
//                         Expanded(
//                           child: ListTileTheme(
//                               contentPadding: const EdgeInsets.all(3),
//                               child: Align(
//                                   alignment: Alignment.centerLeft,
//                                   child: Expanded(
//                                     child: Column(
//                                       crossAxisAlignment: CrossAxisAlignment.start,
//                                       children: [
//                                         Text(date,style: const TextStyle(
//                                             color: Colors.grey)),
//                                         const Padding(
//                                             padding: EdgeInsets.only(
//                                                 top: 10.0, bottom: 10.0)),
//                                         Text(title,
//                                             style: const TextStyle(
//                                                 color: Colors.black)),
//                                         const Padding(
//                                             padding: EdgeInsets.only(
//                                                 top: 10.0, bottom: 10.0)),
//                                         Text("$score%",
//                                             style: const TextStyle(
//                                                 color: Colors.red)),
//                                       ],
//                                     ),
//                                   )
//                               )),
//                         ),
//                         Expanded(
//                           child: ListTileTheme(
//                               textColor: Colors.grey,
//                               contentPadding: const EdgeInsets.all(3),
//                               child: Align(
//                                 alignment: Alignment.centerLeft,
//                                 child: Expanded(
//                                   child: Column(
//                                     crossAxisAlignment: CrossAxisAlignment.start,
//                                     children: [
//                                       Text("$time minutes",style: const TextStyle(
//                                           color: Colors.blue)),
//                                       const Padding(
//                                           padding: EdgeInsets.only(
//                                               top: 10.0, bottom: 10.0)),
//                                       Text(director,
//                                           style: const TextStyle(
//                                               color: Colors.blue)),
//                                       const Padding(
//                                           padding: EdgeInsets.only(
//                                               top: 10.0, bottom: 10.0)),
//                                       Expanded(
//                                           child: Text(description)
//                                       )
//                                     ],
//                                   ),
//                                 )
//                               )),
//                         ),
//                       ],
//                     ),
//                   ),
//                 ),
//               ],
//             ),
//           ),
//         ),
//         elevation: 8,
//         margin: EdgeInsets.all(10),
//       ),
//     );
//   }
// }
