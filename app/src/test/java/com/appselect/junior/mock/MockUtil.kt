package com.appselect.junior.mock

import com.appselect.junior.model.Link
import com.appselect.junior.model.Movie
import com.appselect.junior.model.Multimedia
import com.appselect.junior.model.Response

fun mockMovieList() = listOf(mockMovie())

fun mockMovie() = Movie(
    display_title = "Fight club",
    mpaa_rating = "1337",
    critics_pick = 123,
    byline = "byline",
    headline = "headline",
    summary_short = "summary_short",
    publication_date = "27.06",
    opening_date = "28.06",
    date_updated = "29.06",
    link = mockLink(),
    multimedia = mockMultimedia()
)

fun mockLink() = Link(
    type = "link",
    url = "test.com",
    suggested_link_text = "suggested"
)

fun mockMultimedia() = Multimedia(
    type = "photo",
    src = "test.com",
    height = 100,
    width = 200
)

fun mockResponse() = Response(
    status = "ok",
    copyright = "copyright",
    has_more = true,
    num_results = 20,
    results = mockMovieList()
)