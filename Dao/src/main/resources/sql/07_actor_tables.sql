USE MOVIES;
GO

IF OBJECT_ID('Actor', 'U') IS NOT NULL
    DROP TABLE Actor;
GO

CREATE TABLE Actor (
    IdActor INT IDENTITY(1,1) PRIMARY KEY,
    Name NVARCHAR(100) NOT NULL UNIQUE
);
GO

IF OBJECT_ID('MovieActor', 'U') IS NOT NULL
    DROP TABLE MovieActor;
GO

CREATE TABLE MovieActor (
    IdMovie INT NOT NULL,
    IdActor INT NOT NULL,
    CONSTRAINT PK_MovieActor PRIMARY KEY (IdMovie, IdActor),
    CONSTRAINT FK_MovieActor_Movie
        FOREIGN KEY (IdMovie) REFERENCES Movie(IdMovie),
    CONSTRAINT FK_MovieActor_Actor
        FOREIGN KEY (IdActor) REFERENCES Actor(IdActor)
);
GO

CREATE OR ALTER PROCEDURE dbo.selectActors
AS
BEGIN
    SELECT IdActor, Name
    FROM Actor
    ORDER BY Name;
END;
GO

CREATE OR ALTER PROCEDURE dbo.selectActorsForMovie
    @IdMovie INT
AS
BEGIN
    SELECT a.IdActor, a.Name
    FROM Actor a
    INNER JOIN MovieActor ma ON ma.IdActor = a.IdActor
    WHERE ma.IdMovie = @IdMovie
    ORDER BY a.Name;
END;
GO

CREATE OR ALTER PROCEDURE dbo.addActorToMovie
    @IdMovie INT,
    @IdActor INT
AS
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM MovieActor
        WHERE IdMovie = @IdMovie
          AND IdActor = @IdActor
    )
    BEGIN
        INSERT INTO MovieActor (IdMovie, IdActor)
        VALUES (@IdMovie, @IdActor);
    END
END;
GO

CREATE OR ALTER PROCEDURE dbo.removeActorFromMovie
    @IdMovie INT,
    @IdActor INT
AS
BEGIN
    DELETE FROM MovieActor
    WHERE IdMovie = @IdMovie
      AND IdActor = @IdActor;
END;
GO




