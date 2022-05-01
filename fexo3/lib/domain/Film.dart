class Film {
  final String id;
  final String title;
  final String image;
  final String movie_banner;
  final String description;
  final String director;
  final String release_date;
  final String running_time;
  final String rt_score;



  const Film({
    required this.id,
    required this.title,
    required this.image,
    required this.movie_banner,
    required this.description,
    required this.director,
    required this.release_date,
    required this.running_time,
    required this.rt_score
  });



  factory Film.fromJson(Map<String, dynamic> json) {
    return Film(
      id: json['id'],
      title: json['title'],
      image : json['image'],
      movie_banner : json['movie_banner'],
      description : json['description'],
      director : json['director'],
      release_date : json['release_date'],
      running_time : json['running_time'],
      rt_score : json['rt_score'],

    );
  }

  @override
  String toString() {
    return 'Film{id: $id,'
        ' title: $title,'
        ' image: $image,'
        ' movie_banner: $movie_banner,'
        ' description: $description,'
        ' director: $director,'
        ' release_date: $release_date,'
        ' running_time: $running_time,'
        ' rt_score: $rt_score}';
  }

// @override
  // String toString() {
  //   return "${this.id} : ${this.title}";
  // }
}