class HttpUtil {

    constructor() {
    }

    /**
     * http 기본 요청시 사용하는 메서드
     *
     * data를 null 로 보내면 Request에 data를 담지 않고 전달, type은 GET으로 고정됨.
     *
     * @param url {string}
     * @param type {string}
     * @param data {any[]} json
     * @param successFunction
     */
    defaultRequest(url, type, data, successFunction) {

        if (data === null) {
            $.ajax({
                url: url,
                type: 'GET',
                dataType: "json",
                contentType: 'application/json',
                success: (data) => {
                    successFunction(data);
                },
                error: (err) => {
                    alert(err.responseJSON.message);
                }
            });
        } else {
            $.ajax({
                url: url,
                type: type,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: 'application/json',
                success: (data) => {
                    successFunction(data);
                },
                error: (err) => {
                    alert(err.responseJSON.message);
                }
            });
        }
    }

    /**
     * 업로드 요청시 사용하는 메서드
     *
     * @param url {string}
     * @param type {string}
     * @param data {object} formData
     * @param successFunction
     */
    uploadRequest(url, type, data, successFunction) {
        $.ajax({
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            url: url,
            type: type,
            data: data,
            dataType: "json",
            success: (data) => {
                successFunction(data);
            },
            error: (err) => {
                alert(err.responseJSON.message);
            }
        });
    }

}