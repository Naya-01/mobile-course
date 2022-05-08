import 'dart:convert';
import 'package:flutter/foundation.dart';
import '../domain/Film.dart';
import 'package:http/http.dart' as http;

class FilmsService {

  FilmsService();

   final http.Client client = http.Client();

  Future<List<Film>> getAllFilms() async {
    await Future.delayed(const Duration(seconds: 2));
    final response =
        await client.get(Uri.parse('https://ghibliapi.herokuapp.com/films'));
    // Use the compute function to run parseFilms in a separate isolate.
    return compute(_parseFilms, response.body);
    //return parseFilms(response.body); // if we were not to use an Isolate
  }




  List<Film> _parseFilms(String responseBody) {
    final parsed = jsonDecode(responseBody);
    return parsed.map<Film>((json) => Film.fromJson(json)).toList();
  }
}
