[#ftl]
[@c.page]
    <div class="detail">
        <div class="vizitka">
            <div class="jmeno">${vizitka.celeJmeno}</div>
            <div class="firma">${vizitka.firma}</div>
            <div class="udaje">
                <div class="adresa">
                    <div>${vizitka.ulice}</div>
                    <div><span>${vizitka.psc}</span> <span>${vizitka.obec}</span></div>
                </div>
                <div class="kontakty">
                    [#if vizitka.email?has_content]
                        <div><i class="las la-at"></i> <span>${vizitka.email}</span></div>
                    [/#if]
                    [#if vizitka.telefon?has_content]
                        <div><i class="las la-phone-alt"></i> <span>${vizitka.telefon}</span></div>
                    [/#if]
                    [#if vizitka.web?has_content]
                        <div><i class="las la-globe"></i> <span>${vizitka.web}</span></div>
                    [/#if]
                </div>
            </div>
        </div>

        <div class="mapa">
            <iframe style="border:none" src="https://frame.mapy.cz/?q=${vizitka.celaAdresa()}" width="100%"
                    height="100%" frameborder="0"></iframe>
        </div>
        <form action="/smazat" method="post">
            <button type="submit" class="btn btn-light" name="akce" value="smazat">Smazat</button>
            <input type="hidden" name="id" class="form-control" id="web" value="${id}">
        </form>
        <form action="/upravit" method="get">
            <button type="submit" class="btn btn-light" name="akce" value="upravit">Upravit</button>
            <input type="hidden" name="id" class="form-control" id="web" value="${id}">
        </form>

        [@c.back /]
    </div>
[/@c.page]

