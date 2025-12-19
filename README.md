Course <-> Instructor (User) [Bidireccional]:

Relación: @ManyToOne en Course y @OneToMany en User.

Justificación: Es una relación bidireccional porque es necesario navegar en ambos sentidos: un alumno necesita ver quién imparte un curso, y un instructor necesita acceder a su catálogo de cursos impartidos.

Course <-> Lesson [Bidireccional]:

Relación: @OneToMany en Course y @ManyToOne en Lesson.

Justificación: Una lección no tiene sentido sin su curso. La bidireccionalidad facilita la persistencia en cascada (al guardar un curso se guardan sus lecciones) y la recuperación del temario completo.

Course -> Category [ManyToMany]:

Relación: @ManyToMany (Course es el propietario de la relación).

Justificación: Un curso puede tratar varios temas y una categoría engloba múltiples cursos. Se implementa esta relación para permitir un filtrado flexible de contenidos.

User <-> Enrollment <- Course [Híbrida]:

Relación: El usuario conoce sus matrículas (@OneToMany en User), pero el Curso no almacena una lista pesada de todas las matrículas históricas (Unidireccional desde Enrollment a Course).

Justificación: Enrollment desglosa la relación ManyToMany. Hacemos que User conozca sus matrículas para facilitar la vista "Mis Cursos" del alumno. Sin embargo, evitamos cargar la lista completa de estudiantes en la entidad Course para no penalizar el rendimiento al cargar detalles del curso.

Review -> User / Review -> Course [Unidireccional]:

Relación: @ManyToOne desde Review hacia User y Course.

Justificación: Las entidades User y Course no contienen listas de reviews. Las reviews suelen crecer y es más eficiente obtenerlas mediante consultas al repositorio.
