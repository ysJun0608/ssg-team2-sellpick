

$(document).ready(function() {
    var warehouseData = []; // 창고 데이터를 저장할 배열

    // 페이지 로드 시 창고 데이터 가져오기
    $('#warehouse-data tr').each(function() {
        var type = $(this).find('td:eq(0)').text();
        var id = $(this).find('td:eq(1)').text();
        var deliveryCompanyName = $(this).find('td:eq(2)').text();
        var location = $(this).find('td:eq(3)').text();

        warehouseData.push({
            type: type,
            id: id,
            deliveryCompanyName: deliveryCompanyName,
            location: location
        });
    });

    // 검색창에 자동완성 기능 추가
    $('input[name="keyword"]').autocomplete({
        source: function(request, response) {
            var category = $('select[name="type"]').val();
            var keyword = request.term.toLowerCase();

            var filteredData = warehouseData.filter(function(item) {
                if (category === 'id') {
                    return item.id.toLowerCase().includes(keyword);
                } else if (category === 'location') {
                    return item.location.toLowerCase().includes(keyword);
                } else if (category === 'type') {
                    return item.type.toLowerCase().includes(keyword);
                } else if (category === 'deliveryCompanyName') {
                    return item.deliveryCompanyName.toLowerCase().includes(keyword);
                }
                return false;
            });

            response(filteredData.map(function(item) {
                if (category === 'id') {
                    return item.id;
                } else if (category === 'location') {
                    return item.location;
                } else if (category === 'type') {
                    return item.type;
                } else if (category === 'deliveryCompanyName') {
                    return item.deliveryCompanyName;
                }
                return '';
            }));
        },
        minLength: 1 // 자동완성 표시를 위한 최소 문자 수
    });
});