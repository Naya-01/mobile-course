import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class FilmModel extends ChangeNotifier {
  late List<bool> listBool;
  bool _check =false;
  bool get check => _check;

  Future<void> initBooleanList(int size) async {
    listBool = List.filled(size, false);
    notifyListeners();
  }

  Future<void> changeAllBoolean() async {
    listBool = List.filled(listBool.length, !_check);
    _check = !_check;
    notifyListeners();
  }

  Future<void> changeBoolean(int i) async {
    listBool[i]=!listBool[i];
    notifyListeners();
  }
}
