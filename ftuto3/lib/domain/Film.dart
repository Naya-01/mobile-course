class Film {
  final int id;
  final String title;
  final int duration;
  final int budget;
  final String link;

  const Film({
    required this.id,
    required this.title,
    required this.duration,
    required this.budget,
    required this.link,
  });

  factory Film.fromJson(Map<String, dynamic> json) {
    return Film(
      id: json['id'],
      title: json['title'],
      duration: json['duration'],
      budget: json['budget'],
      link: json['link'],
    );
  }
}