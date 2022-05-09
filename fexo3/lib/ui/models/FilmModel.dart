import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import '../../domain/Film.dart';
import '../../services/FilmsService.dart';

class FilmModel extends ChangeNotifier {
  final FilmsService movies = FilmsService();

  late List<bool> listBool;
  bool _check = false;
  bool get check => _check;

  late Future<List<Film>> _films;
  Future<List<Film>> get films => _films;

  void initBooleanList(int size) async {
    listBool = List.filled(size, _check);
  }

  Future<void> changeAllBoolean() async {
    listBool = List.filled(listBool.length, !_check);
    _check = !_check;
    notifyListeners();
  }

  Future<void> changeBoolean(int i) async {
    listBool[i] = !listBool[i];
    notifyListeners();
  }

  Future<void> getAllFilm() async {
    _films = movies.getAllFilms();
  }
}
