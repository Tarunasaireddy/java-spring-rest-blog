 public List<Post> randomPosts = new ArrayList<>();
    public List<Author> authors = new ArrayList<>();

    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public DatabaseLoader(PostRepository postRepository, AuthorRepository authorRepository) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        authors.addAll(Arrays.asList(
                new Author("sholderness", "Sarah",  "Holderness", "password"),
                new Author("tbell", "Tom",  "Bell", "password"),
                new Author("efisher", "Eric",  "Fisher", "password"),
                new Author("csouza", "Carlos",  "Souza", "password")
        ));

        authorRepository.saveAll(authors);

        IntStream.range(0,40).forEach(i->{
            String template = templates[i % templates.length];
            String gadget = gadgets[i % gadgets.length];
            Author author = authors.get(i % authors.size());

            String title = String.format(template, gadget);
            Post post = new Post(title, "Lorem ipsum dolor sit amet, consectetur adipiscing elit… ");
            post.setAuthor(author);
            author.addPost(post);
            randomPosts.add(post);
        });

        postRepository.saveAll(randomPosts);
        authorRepository.saveAll(authors);
    }
}
