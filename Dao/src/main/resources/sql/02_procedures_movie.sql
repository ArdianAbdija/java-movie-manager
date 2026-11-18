/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Ardian
 * Created: Jan 26, 2026
 */

-- 02_procedures_movie.sql
USE MOVIES;
GO

CREATE OR ALTER PROCEDURE dbo.createMovie
    @Title NVARCHAR(300),
    @Link NVARCHAR(1000),
    @Description NVARCHAR(MAX),
    @PicturePath NVARCHAR(1000),
    @PublishedDate NVARCHAR(50),
    @IDMovie INT OUTPUT
AS
BEGIN
    SET NOCOUNT ON;

    INSERT INTO dbo.Movie (Title, Link, Description, PicturePath, PublishedDate)
    VALUES (
        @Title,
        @Link,
        @Description,
        @PicturePath,
        TRY_CONVERT(DATETIME2, @PublishedDate, 126)
    );

    SET @IDMovie = SCOPE_IDENTITY();
END
GO

CREATE OR ALTER PROCEDURE dbo.updateMovie
    @Title NVARCHAR(300),
    @Link NVARCHAR(1000),
    @Description NVARCHAR(MAX),
    @PicturePath NVARCHAR(1000),
    @PublishedDate NVARCHAR(50),
    @IDMovie INT
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE dbo.Movie
    SET
        Title = @Title,
        Link = @Link,
        Description = @Description,
        PicturePath = @PicturePath,
        PublishedDate = TRY_CONVERT(DATETIME2, @PublishedDate, 126)
    WHERE IDMovie = @IDMovie;
END
GO

CREATE OR ALTER PROCEDURE dbo.deleteMovie
    @IDMovie INT
AS
BEGIN
    SET NOCOUNT ON;
    DELETE FROM dbo.Movie WHERE IDMovie = @IDMovie;
END
GO

CREATE OR ALTER PROCEDURE dbo.selectMovie
    @IDMovie INT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT
        IDMovie,
        Title,
        Link,
        Description,
        PicturePath,
        CONVERT(VARCHAR(33), PublishedDate, 126) AS PublishedDate
    FROM dbo.Movie
    WHERE IDMovie = @IDMovie;
END
GO

CREATE OR ALTER PROCEDURE dbo.selectMovies
AS
BEGIN
    SET NOCOUNT ON;

    SELECT
        IDMovie,
        Title,
        Link,
        Description,
        PicturePath,
        CONVERT(VARCHAR(33), PublishedDate, 126) AS PublishedDate
    FROM dbo.Movie
    ORDER BY PublishedDate DESC;
END
GO

CREATE OR ALTER PROCEDURE dbo.deleteAllMovies
AS
BEGIN
    SET NOCOUNT ON;
    DELETE FROM dbo.Movie;
END
GO




