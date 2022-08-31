package mapper

import com.example.data.datasource.remote.model.Data
import com.example.data.datasource.remote.model.Results
import model.Hero
import model.HeroPaging


fun Data.toDomain() = HeroPaging(
    offset = this.offset,
    limit = this.limit,
    total = this.total,
    results = this.results.map {
        Hero(
            id = it.id,
            name = it.name,
            description = it.description,
            thumbnail = with(it.thumbnail) { "${path.replace("http", "https")}.$extension" }
        )
    }
)

fun Results.toDomain() = Hero(
    id = id,
    name = name,
    description = description,
    thumbnail = with(thumbnail) { "${path.replace("http", "https")}.$extension" }
)