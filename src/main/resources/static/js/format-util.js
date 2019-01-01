

function getJsonContent(json, key) {
    if (json.hasOwnProperty(key)) {
        return JSON.stringify(json[key])
    } else {
        return JSON.stringify(JSON.parse('{}'))
    }
}

function getStringContent(json, key) {
    if (json.hasOwnProperty(key)) {
        return json[key]
    } else {
        return ''
    }
}